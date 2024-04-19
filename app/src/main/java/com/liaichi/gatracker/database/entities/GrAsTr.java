package com.liaichi.gatracker.database.entities;

import android.util.Log;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.liaichi.gatracker.database.GATDatabase;
import com.liaichi.gatracker.database.GATRepository;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(tableName = GATDatabase.GAT_TABLE)
public class GrAsTr {
  @PrimaryKey(autoGenerate = true)
  private int acceptId;
  private LocalDateTime date;
  private int userId;
  private int assignId;
  private int grade;
  private String feedback;

  public GrAsTr(int userId, int assignId){
    this.userId = userId;
    this.assignId = assignId;
    date = LocalDateTime.now();
    grade = 0;
  }

  @Override
  public String toString() {
    //TODO: make this return Assignment Name and the Grade the student got for that assignment.
    return
        "Assignment id: " + assignId + "\n" +
        "Date accepted: " + date + "\n" +
        "grade: " + grade + "\n" +
            "feedback: " + "\n" +
          "=-=-=-=-=-=-=\n";
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GrAsTr grAsTr = (GrAsTr) o;
    return acceptId == grAsTr.acceptId && userId == grAsTr.userId && assignId == grAsTr.assignId
        && grade == grAsTr.grade && Objects.equals(date, grAsTr.date)
        && Objects.equals(feedback, grAsTr.feedback);
  }

  @Override
  public int hashCode() {
    return Objects.hash(acceptId, date, userId, assignId, grade, feedback);
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getAcceptId() {
    return acceptId;
  }

  public void setAcceptId(int acceptId) {
    this.acceptId = acceptId;
  }

  public int getAssignId() {
    return assignId;
  }

  public void setAssignId(int assignId) {
    this.assignId = assignId;
  }

  public int getGrade() {
    return grade;
  }

  public void setGrade(int grade) {
    this.grade = grade;
  }

  public String getFeedback() {
    return feedback;
  }

  public void setFeedback(String feedback) {
    this.feedback = feedback;
  }
}
