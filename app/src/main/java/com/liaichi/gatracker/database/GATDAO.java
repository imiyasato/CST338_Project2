package com.liaichi.gatracker.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.liaichi.gatracker.database.entities.GrAsTr;
import com.liaichi.gatracker.database.entities.User;
import java.util.List;

@Dao
public interface GATDAO {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(GrAsTr grastr);

  @Update
  void update(GrAsTr grastr);

  @Query("SELECT * FROM " + GATDatabase.GAT_TABLE + " ORDER BY date DESC")
  List<GrAsTr> getAllRecords();


  @Query("SELECT * FROM " + GATDatabase.GAT_TABLE
      + " WHERE userId = :loggedInUserId ORDER BY date DESC")
  LiveData<List<GrAsTr>> getRecordsByUserIdLiveData(int loggedInUserId);


  @Query("SELECT * FROM " + GATDatabase.GAT_TABLE
      + " WHERE userId == :userId AND assignId == :assignId")
  LiveData<GrAsTr> getRecordByUserAndAssign(int userId, int assignId);
}
