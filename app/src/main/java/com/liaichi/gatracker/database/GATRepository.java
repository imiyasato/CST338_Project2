package com.liaichi.gatracker.database;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.LiveData;
import com.liaichi.gatracker.LandingPageActivity;
import com.liaichi.gatracker.database.entities.Course;
import com.liaichi.gatracker.database.entities.GrAsTr;
import com.liaichi.gatracker.database.entities.User;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GATRepository {

  private final GATDAO gatdao;
  private final UserDAO userDAO;
  private final CourseDAO courseDAO;


  private ArrayList<GrAsTr> allLogs;

  private static GATRepository repository;


  private GATRepository(Application application) {
    GATDatabase db = GATDatabase.getDatabase(application);
    this.gatdao = db.GATDAO();
    this.userDAO = db.userDAO();
    this.courseDAO = db.courseDAO();
    this.allLogs = (ArrayList<GrAsTr>) this.gatdao.getAllRecords();
  }


  public static GATRepository getRepository(Application application) {
    if (repository != null) {
      return repository;
    }
    Future<GATRepository> future = GATDatabase.databaseWriteExecutor.submit(
        new Callable<GATRepository>() {
          @Override
          public GATRepository call() throws Exception {
            return new GATRepository(application);
          }
        });
    try {
      return future.get();
    } catch (InterruptedException | ExecutionException e) {
      Log.i(LandingPageActivity.TAG, "Problem getting GATRepository. Thread error.");
    }
    return null;
  }

  public ArrayList<GrAsTr> getAllLogs() {
    Future<ArrayList<GrAsTr>> future = GATDatabase.databaseWriteExecutor.submit(
        new Callable<ArrayList<GrAsTr>>() {
          @Override
          public ArrayList<GrAsTr> call() throws Exception {
            return (ArrayList<GrAsTr>) gatdao.getAllRecords();
          }
        });
    try {
      return future.get();
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
      Log.i(LandingPageActivity.TAG, "Problem when getting all GAT info in the repository");
    }
    return null;
  }

  public void insertGAT(GrAsTr grastr) {
    GATDatabase.databaseWriteExecutor.execute(() -> {
      gatdao.insert(grastr);
    });
  }

  public void insertUser(User... user) {
    GATDatabase.databaseWriteExecutor.execute(() -> {
      userDAO.insert(user);
    });
  }

  public void insertCourse(Course... course){
    GATDatabase.databaseWriteExecutor.execute(()->{
      courseDAO.insert(course);
    });
  }

  public LiveData<User> getUserByUserName(String username) {

    return userDAO.getUserByUserName(username);

  }

  public LiveData<User> getUserByUserId(int userId) {

    return userDAO.getUserByUserId(userId);

  }

  public LiveData<List<GrAsTr>> getAllLogsByUserIdLiveData(int loggedInUserId){
    return gatdao.getRecordsByUserIdLiveData(loggedInUserId);
  }

}
