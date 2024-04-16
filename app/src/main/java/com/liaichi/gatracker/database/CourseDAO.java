package com.liaichi.gatracker.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.liaichi.gatracker.database.entities.Course;
import com.liaichi.gatracker.database.entities.GrAsTr;
import com.liaichi.gatracker.database.entities.User;
import java.util.List;

@Dao
public interface CourseDAO {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(Course... course);


  @Delete
  void delete(Course course);

  @Query("SELECT * FROM " + GATDatabase.COURSE_TABLE + " ORDER BY courseName")
  LiveData<List<Course>> getAllUsers();

  @Query("DELETE FROM " + GATDatabase.COURSE_TABLE)
  void deleteAll();

  @Query("SELECT * FROM " + GATDatabase.COURSE_TABLE + " WHERE courseName == :courseName")
  LiveData<Course> getUserByUserName(String courseName);

  @Query("SELECT * FROM " + GATDatabase.COURSE_TABLE + " WHERE courseId == :courseId")
  LiveData<Course> getUserByUserId(int courseId);
}
