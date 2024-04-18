package com.liaichi.gatracker.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.liaichi.gatracker.database.entities.Course;
import com.liaichi.gatracker.database.entities.GrAsTr;
import com.liaichi.gatracker.database.entities.User;
import java.util.List;

@Dao
public interface CourseDAO {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(Course... course);

  @Update
  void update(Course course);

  @Delete
  void delete(Course course);

  @Query("SELECT * FROM " + GATDatabase.COURSE_TABLE + " ORDER BY courseName")
  LiveData<List<Course>> getAllCourses();

  @Query("DELETE FROM " + GATDatabase.COURSE_TABLE)
  void deleteAll();

  @Query("SELECT * FROM " + GATDatabase.COURSE_TABLE + " WHERE courseName == :courseName")
  LiveData<Course> getCourseByCourseName(String courseName);

  @Query("SELECT * FROM " + GATDatabase.COURSE_TABLE + " WHERE courseId == :courseId")
  LiveData<Course> getCourseByCourseId(int courseId);

  @Query("SELECT * FROM " + GATDatabase.COURSE_TABLE
      + " WHERE managerId = :userId")
  LiveData<Course> getCourseByUserId(int userId);
}
