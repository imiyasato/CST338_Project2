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
import com.liaichi.gatracker.databinding.ActivityGradeAssignmentBinding;

public class GradeAssignmentActivity extends AppCompatActivity {

  private static final String GRADE_ASSIGN_ACTIVITY_USER_ID = "com.liaichi.gatracker.GRADE_ASSIGN_ACTIVITY_USER_ID";
  ActivityGradeAssignmentBinding binding;
  private GATRepository repository;

  private int loggedInUserId = getIntent().getIntExtra(GRADE_ASSIGN_ACTIVITY_USER_ID, -1);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    binding = ActivityGradeAssignmentBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());


    repository = GATRepository.getRepository(getApplication());
  }

  static Intent gradeAssignmentIntentFactory(Context context, int userId) {
    Intent intent = new Intent(context, GradeAssignmentActivity.class);
    intent.putExtra(GRADE_ASSIGN_ACTIVITY_USER_ID, userId);
    return intent;
  }
}