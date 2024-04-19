package com.liaichi.gatracker.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.liaichi.gatracker.database.entities.User;
import java.util.List;

@Dao
public interface UserDAO {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(User... user);

  @Update
  void update(User user);

  @Delete
  void delete(User user);

  @Query("SELECT * FROM " + GATDatabase.USER_TABLE + " ORDER BY username")
  LiveData<List<User>> getAllUsers();

  @Query("DELETE FROM " + GATDatabase.USER_TABLE)
  void deleteAll();

  @Query("SELECT * FROM " + GATDatabase.USER_TABLE + " WHERE username == :username")
  LiveData<User> getUserByUserName(String username);

  @Query("SELECT * FROM " + GATDatabase.USER_TABLE + " WHERE username == :username")
  User getnlUserByUserName(String username);

  @Query("SELECT * FROM " + GATDatabase.USER_TABLE + " WHERE id == :userId")
  LiveData<User> getUserByUserId(int userId);



}
