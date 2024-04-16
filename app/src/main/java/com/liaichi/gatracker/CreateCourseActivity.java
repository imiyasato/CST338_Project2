package com.liaichi.gatracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.LiveData;
import com.liaichi.gatracker.database.GATRepository;
import com.liaichi.gatracker.database.entities.Course;
import com.liaichi.gatracker.database.entities.User;
import com.liaichi.gatracker.databinding.ActivityCreateCourseBinding;

public class CreateCourseActivity extends AppCompatActivity {
  private ActivityCreateCourseBinding binding;
  private GATRepository repository;

  private String courseName;
  private String courseManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityCreateCourseBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    repository = GATRepository.getRepository(getApplication());
    binding.publishCourseButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        getInformationFromDisplay();
        insertNewCourse();
      }
    });
  }

  private void getInformationFromDisplay() {
    this.courseName = binding.courseNameEditText.getText().toString();
    this.courseManager = binding.courseManagerEditText.getText().toString();
  }

  private void insertNewCourse(){
    if(this.courseName.isEmpty()){
      toastMaker("Course name cannot be empty");
      return;
    }

    if(this.courseManager.isEmpty()){
      toastMaker("Course manager cannot be empty");
      return;
    }

    LiveData<User> userObserver = repository.getUserByUserName(courseManager);
    userObserver.observe(this, user -> {
      if (user != null) {
        if(!user.isAdmin() && !user.isTeacher()){
          toastMaker("Course manager must be either a teacher or admin");
          return;
        }
        else {
          toastMaker("Course successfully made.");
          Course createdCourse = new Course(this.courseName,user.getId());
          repository.insertCourse(createdCourse);
        }
      }
      else {
        toastMaker("That user doesn't exist.");
        return;
      }
    });
  }

  private void toastMaker(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }

  static Intent createCourseIntentFactory(Context context) {
    return new Intent(context, CreateCourseActivity.class);
  }
}