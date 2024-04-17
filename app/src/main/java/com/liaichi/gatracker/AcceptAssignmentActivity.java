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
import com.liaichi.gatracker.databinding.ActivityAcceptAssignmentBinding;

public class AcceptAssignmentActivity extends AppCompatActivity {

  private static final String ACCEPT_ASSIGN_ACTIVITY_USER_ID = "com.liaichi.gatracker.ACCEPT_ASSIGN_ACTIVITY_USER_ID";
  ActivityAcceptAssignmentBinding binding;
  private GATRepository repository;
  private int loggedInUserId;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityAcceptAssignmentBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    loggedInUserId = getIntent().getIntExtra(ACCEPT_ASSIGN_ACTIVITY_USER_ID, -1);
    repository = GATRepository.getRepository(getApplication());
  }

  static Intent acceptAssignmentIntentFactory(Context context, int userId) {
    Intent intent = new Intent(context, AcceptAssignmentActivity.class);
    intent.putExtra(ACCEPT_ASSIGN_ACTIVITY_USER_ID, userId);
    return intent;
  }
}