package com.liaichi.gatracker.database;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.liaichi.gatracker.LandingPageActivity;
import com.liaichi.gatracker.database.entities.Course;
import com.liaichi.gatracker.database.entities.GrAsTr;
import com.liaichi.gatracker.database.entities.User;
import com.liaichi.gatracker.database.typeConverters.LocalDateTypeConverter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@TypeConverters(LocalDateTypeConverter.class)
@Database(entities = {GrAsTr.class, User.class, Course.class}, version = 1, exportSchema = false)
public abstract class GATDatabase extends RoomDatabase {

  public static final String DATABASE_NAME = "GATDatabase";
  public static final String USER_TABLE = "usertable";
  public static final String COURSE_TABLE = "coursetable";
  public static final String GAT_TABLE = "gattable";
  public static final String ASSIGNMENT_TABLE = "assignmenttable";

  private static volatile GATDatabase INSTANCE;
  private static final int NUMBER_OF_THREADS = 4;
  static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(
      NUMBER_OF_THREADS);

  static GATDatabase getDatabase(final Context context) {
    if (INSTANCE == null) {
      synchronized (GATDatabase.class) {
        if (INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(), GATDatabase.class,
              DATABASE_NAME).fallbackToDestructiveMigration().addCallback(addDefaultValues).build();
        }
      }
    }
    return INSTANCE;
  }

  private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback() {
    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);
      Log.i(LandingPageActivity.TAG, "DATABASE CREATED!");
      databaseWriteExecutor.execute(() -> {
            UserDAO dao = INSTANCE.userDAO();
            dao.deleteAll();
            User testUser1 = new User("testuser1", "testuser1");
            dao.insert(testUser1);
            User admin2 = new User("admin2", "admin2");
            admin2.setAdmin(true);
            dao.insert(admin2);
            User teacher3 = new User("teacher3", "teacher3");
            teacher3.setTeacher(true);
            dao.insert(teacher3);
          }
      );
    }
  };

  public abstract GATDAO GATDAO();

  public abstract UserDAO userDAO();

  public abstract CourseDAO courseDAO();
}
