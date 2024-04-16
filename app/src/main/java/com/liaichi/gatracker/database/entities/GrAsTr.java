package com.liaichi.gatracker.database.entities;

import android.util.Log;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.liaichi.gatracker.database.GATDatabase;
import java.time.LocalDateTime;

@Entity(tableName = GATDatabase.GAT_TABLE)
public class GrAsTr {
  @PrimaryKey(autoGenerate = true)
  private int acceptId;
  private LocalDateTime date;
  private int userId;
  private int assignId;
  private int grade;

  public GrAsTr(int userId, int assignId){
    this.userId = userId;
    this.assignId = assignId;
    date = LocalDateTime.now();
  }
//
//  public GymLog(String exercise, double weight, int reps, int userId) {
//    this.exercise = exercise;
//    this.weight = weight;
//    this.reps = reps;
//    this.userId = userId;
//    date = LocalDateTime.now();
//  }
//
//  @NonNull
//  @Override
//  public String toString() {
//    return exercise + "\n" + "weight: " + weight + "\n" + "reps: " + reps + "\n" + "date: "
//        + date.toString() + "\n" + "=-=-=-=-=-=-=\n";
//  }
//
//  @Override
//  public boolean equals(Object o) {
//    if (this == o) {
//      return true;
//    }
//    if (o == null || getClass() != o.getClass()) {
//      return false;
//    }
//    GymLog gymLog = (GymLog) o;
//    return id == gymLog.id && Double.compare(weight, gymLog.weight) == 0
//        && reps == gymLog.reps && userId == gymLog.userId && Objects.equals(exercise,
//        gymLog.exercise) && Objects.equals(date, gymLog.date);
//  }
//
//  @Override
//  public int hashCode() {
//    return Objects.hash(id, exercise, weight, reps, date, userId);
//  }
//
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
//
//  public String getExercise() {
//    return exercise;
//  }
//
//  public void setExercise(String exercise) {
//    this.exercise = exercise;
//  }
//
//  public double getWeight() {
//    return weight;
//  }
//
//  public void setWeight(double weight) {
//    this.weight = weight;
//  }
//
//  public int getReps() {
//    return reps;
//  }
//
//  public void setReps(int reps) {
//    this.reps = reps;
//  }
//
  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }
//
//
  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }
}
