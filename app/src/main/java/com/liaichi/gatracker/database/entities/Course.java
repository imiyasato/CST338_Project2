package com.liaichi.gatracker.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.liaichi.gatracker.database.GATDatabase;
import java.util.Objects;

@Entity(tableName = GATDatabase.COURSE_TABLE)
public class Course {
  @PrimaryKey(autoGenerate = true)
  private int courseId;
  private int managerId; // The user managing the course
  private String courseName;

  public Course(String courseName, int managerId) {
    this.courseName = courseName;
    this.managerId = managerId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Course course = (Course) o;
    return courseId == course.courseId && managerId == course.managerId && Objects.equals(
        courseName, course.courseName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseId, managerId, courseName);
  }

  public int getCourseId() {
    return courseId;
  }

  public void setCourseId(int courseId) {
    this.courseId = courseId;
  }

  public int getManagerId() {
    return managerId;
  }

  public void setManagerId(int managerId) {
    this.managerId = managerId;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }
}
