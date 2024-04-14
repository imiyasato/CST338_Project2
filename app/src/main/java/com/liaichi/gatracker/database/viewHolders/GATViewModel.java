package com.liaichi.gatracker.database.viewHolders;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.liaichi.gatracker.database.GATRepository;
import com.liaichi.gatracker.database.entities.GrAsTr;
import java.util.List;

public class GATViewModel  extends AndroidViewModel {
  private final GATRepository repository;


  public GATViewModel (Application application){
    super(application);
    repository = GATRepository.getRepository(application);

  }

  public LiveData<List<GrAsTr>> getAllLogsById(int userId) {
    return repository.getAllLogsByUserIdLiveData(userId);
  }

  public void insert(GrAsTr log){
    repository.insertGAT(log);
  }
}
