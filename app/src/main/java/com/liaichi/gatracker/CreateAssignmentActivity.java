package com.liaichi.gatracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import com.liaichi.gatracker.database.GATRepository;
import com.liaichi.gatracker.database.entities.Assignment;
import com.liaichi.gatracker.database.entities.Course;
import com.liaichi.gatracker.database.entities.User;
import com.liaichi.gatracker.databinding.ActivityAcceptAssignmentBinding;
import com.liaichi.gatracker.databinding.ActivityCreateAssignmentBinding;

public class CreateAssignmentActivity extends AppCompatActivity {

  private static final String CREATE_ASSIGN_ACTIVITY_USER_ID = "com.liaichi.gatracker.CREATE_ASSIGN_ACTIVITY_USER_ID";

  ActivityCreateAssignmentBinding binding;

  private GATRepository repository;

  private String assignTitle;
  private int loggedInUserId;
  private int dueDate = -1;

  private Course course;
  private int courseId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityCreateAssignmentBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    loggedInUserId = getIntent().getIntExtra(CREATE_ASSIGN_ACTIVITY_USER_ID, -1);
    repository = GATRepository.getRepository(getApplication());

    binding.publishAssignmentButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        getInformationFromDisplay();
        insertNewAssignment();
      }
    });
  }

  private void getInformationFromDisplay() {
    this.assignTitle = binding.assignmentTitleEditText.getText().toString();
    try {

      this.dueDate = Integer.parseInt(binding.dueDateEditText.getText().toString());
    } catch (NumberFormatException e) {
      Log.d(LandingPageActivity.TAG, "Error reading value from Due Date edit text");
    }
  }

  private void insertNewAssignment() {
    if (this.assignTitle.isEmpty()) {
      toastMaker("Assignment Title cannot be blank");
      return;
    }
    if (this.dueDate < 0) {
      toastMaker("There is a problem with the due date");
      return;
    }

    LiveData<Course> courseObserver = repository.getCourseByUserId(loggedInUserId);
    courseObserver.observe(this, course -> {
      this.course = course;
      if (this.course == null) {
        toastMaker("This user is not managing a course");
        return;
      } else {
        courseId = this.course.getCourseId();
        Assignment createdAssignment = new Assignment(courseId,this.assignTitle,this.dueDate);
        repository.insertAssignment(createdAssignment);
        toastMaker("Assignment successfully made");
      }
    });

  }


  private void toastMaker(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }

  static Intent createAssignmentIntentFactory(Context context, int userId) {
    Intent intent = new Intent(context, CreateAssignmentActivity.class);
    intent.putExtra(CREATE_ASSIGN_ACTIVITY_USER_ID, userId);
    return intent;
  }
}