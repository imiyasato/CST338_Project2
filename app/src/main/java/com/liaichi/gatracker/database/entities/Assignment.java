package com.liaichi.gatracker.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.liaichi.gatracker.database.GATDatabase;
import java.util.Objects;

@Entity(tableName = GATDatabase.ASSIGNMENT_TABLE)
public class Assignment {

  @PrimaryKey(autoGenerate = true)
  private int assignmentId;
  private int courseId;
  private String assignmentName;
  private int dueDate;

  public Assignment(int courseId, String assignmentName, int dueDate) {
    this.courseId = courseId;
    this.assignmentName = assignmentName;
    this.dueDate = dueDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Assignment that = (Assignment) o;
    return assignmentId == that.assignmentId && courseId == that.courseId && dueDate == that.dueDate
        && Objects.equals(assignmentName, that.assignmentName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(assignmentId, courseId, assignmentName, dueDate);
  }

  public int getAssignmentId() {
    return assignmentId;
  }

  public void setAssignmentId(int assignmentId) {
    this.assignmentId = assignmentId;
  }

  public int getCourseId() {
    return courseId;
  }

  public void setCourseId(int courseId) {
    this.courseId = courseId;
  }

  public String getAssignmentName() {
    return assignmentName;
  }

  public void setAssignmentName(String assignmentName) {
    this.assignmentName = assignmentName;
  }

  public int getDueDate() {
    return dueDate;
  }

  public void setDueDate(int dueDate) {
    this.dueDate = dueDate;
  }
}
