package com.liaichi.gatracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.liaichi.gatracker.database.GATRepository;
import com.liaichi.gatracker.databinding.ActivityCreateCourseBinding;

public class CreateCourseActivity extends AppCompatActivity {
  private ActivityCreateCourseBinding binding;
  private GATRepository repository;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityCreateCourseBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

  }

  static Intent createCourseIntentFactory(Context context) {
    return new Intent(context, CreateCourseActivity.class);
  }
}