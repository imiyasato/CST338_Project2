package com.liaichi.gatracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.liaichi.gatracker.database.GATRepository;
import com.liaichi.gatracker.database.entities.User;
import com.liaichi.gatracker.databinding.ActivityViewGradesBinding;

public class ViewGradesActivity extends AppCompatActivity {

  private static final String VIEW_GRADES_ACTIVITY_USER_ID = "com.liaichi.gatracker.VIEW_GRADES_ACTIVITY_USER_ID";
  ActivityViewGradesBinding binding;

  private GATRepository repository;

  private int loggedInUserId;
  private User user;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityViewGradesBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    loggedInUserId= getIntent().getIntExtra(VIEW_GRADES_ACTIVITY_USER_ID, -1);
    repository = GATRepository.getRepository(getApplication());

  }

  static Intent viewGradesIntentFactory(Context context, int userId) {
    Intent intent = new Intent(context, ViewGradesActivity.class);
    intent.putExtra(VIEW_GRADES_ACTIVITY_USER_ID, userId);
    return intent;
  }
}