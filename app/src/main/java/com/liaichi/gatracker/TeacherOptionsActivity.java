package com.liaichi.gatracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.liaichi.gatracker.database.GATRepository;
import com.liaichi.gatracker.databinding.ActivityGradeAssignmentBinding;
import com.liaichi.gatracker.databinding.ActivityTeacherOptionsBinding;

public class TeacherOptionsActivity extends AppCompatActivity {

  private static final String TEACHER_OPTIONS_ACTIVITY_USER_ID = "com.liaichi.gatracker.TEACHER_OPTIONS_ACTIVITY_USER_ID";
  ActivityTeacherOptionsBinding binding;

  private GATRepository repository;
  private int loggedInUserId;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityTeacherOptionsBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    loggedInUserId = getIntent().getIntExtra(TEACHER_OPTIONS_ACTIVITY_USER_ID, -1);
    repository = GATRepository.getRepository(getApplication());

    binding.createAssignmentButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = CreateAssignmentActivity.createAssignmentIntentFactory(
            getApplicationContext());
        startActivity(intent);
      }
    });

    binding.gradeAssignmentButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = GradeAssignmentActivity.gradeAssignmentIntentFactory(
            getApplicationContext(), loggedInUserId);
        startActivity(intent);
      }
    });
  }

  static Intent teacherOptionsIntentFactory(Context context, int userId) {
    Intent intent = new Intent(context, TeacherOptionsActivity.class);
    intent.putExtra(TEACHER_OPTIONS_ACTIVITY_USER_ID, userId);
    return intent;
  }
}

