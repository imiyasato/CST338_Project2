package com.liaichi.gatracker.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.liaichi.gatracker.database.entities.Assignment;
import com.liaichi.gatracker.database.entities.Course;
import java.util.List;

@Dao
public interface AssignmentDAO {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(Assignment... assignment);


  @Delete
  void delete(Assignment assignment);

  @Query("SELECT * FROM " + GATDatabase.ASSIGNMENT_TABLE + " ORDER BY assignmentName")
  LiveData<List<Assignment>> getAllUsers();

  @Query("DELETE FROM " + GATDatabase.ASSIGNMENT_TABLE)
  void deleteAll();

  @Query("SELECT * FROM " + GATDatabase.ASSIGNMENT_TABLE + " WHERE assignmentName == :assignmentName")
  LiveData<Course> getAssignmentByAssignmentName(String assignmentName);

  @Query("SELECT * FROM " + GATDatabase.COURSE_TABLE + " WHERE assignmentId == :assignmentId")
  LiveData<Course> getAssignmentByAssignmentId(int assignmentId);
}
