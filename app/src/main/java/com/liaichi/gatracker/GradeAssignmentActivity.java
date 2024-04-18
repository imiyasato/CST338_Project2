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
import com.liaichi.gatracker.database.entities.GrAsTr;
import com.liaichi.gatracker.database.entities.User;
import com.liaichi.gatracker.databinding.ActivityGradeAssignmentBinding;

public class GradeAssignmentActivity extends AppCompatActivity {

  private static final String GRADE_ASSIGN_ACTIVITY_USER_ID = "com.liaichi.gatracker.GRADE_ASSIGN_ACTIVITY_USER_ID";
  ActivityGradeAssignmentBinding binding;
  private GATRepository repository;

  private int loggedInUserId;
  private String enteredUser;
  private String enteredAssign;
  private int grade = -1;
  private int studentId;
  private int assignId;
  private String feedback;

  private GrAsTr modGrastr;
  private User user;
  private Assignment assignment;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    binding = ActivityGradeAssignmentBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    loggedInUserId = getIntent().getIntExtra(GRADE_ASSIGN_ACTIVITY_USER_ID, -1);

    repository = GATRepository.getRepository(getApplication());

    binding.submitGradeButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        getInformationFromDisplay();
//        enterGrade();
      }
    });

    binding.submitFeedbackButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        getInformationFromDisplay();
      }
    });
  }

  private void getInformationFromDisplay() {
    this.enteredUser = binding.studentToGrade.getText().toString();
    this.enteredAssign = binding.assignmentGradeTitle.getText().toString();
    try {
      this.grade = Integer.parseInt(binding.gradeTitle.getText().toString());
    } catch (NumberFormatException e) {
      Log.d(LandingPageActivity.TAG, "Error reading value from Grade edit text");
    }
    this.feedback = binding.enterFeedback.getText().toString();
  }
//
//  private void enterGrade() {
//    int fail = 0;
//    if (this.enteredUser.isEmpty()) {
//      toastMaker("username cannot be blank");
//      return;
//    }
//    if (this.enteredAssign.isEmpty()) {
//      toastMaker("assignment cannot be blank");
//      return;
//    }
//    if (this.grade < 0) {
//      toastMaker("Grade cannot be negative");
//      return;
//    }
//    //Get specific assignment
//    //need to get user id and assignment id
//    LiveData<User> userObserver = repository.getUserByUserName(enteredUser);
//    userObserver.observe(this, user -> {
//      this.user = user;
//      if (this.user == null) {
//        toastMaker("The entered user does not exist");
//      } else {
//        studentId = this.user.getId();
//      }
//    });
//
//    LiveData<Assignment> assignObserver = repository.getAssignmentByAssignmentName(enteredAssign);
//    assignObserver.observe(this, assignment -> {
//      this.assignment = assignment;
//      if (this.assignment == null) {
//        toastMaker("Assignment not found");
//        return;
//      } else {
//        assignId = this.assignment.getAssignmentId();
//      }
//    });
//
//    LiveData<GrAsTr> gatObserver = repository.getRecordByUserAndAssign(studentId, assignId);
//    gatObserver.observe(this, grAsTr -> {
//      this.modGrastr = grAsTr;
//      if (this.modGrastr == null) {
//        toastMaker("The user has not accepted that assignment.");
//        return;
//      }
//    });
//    modGrastr.setGrade(this.grade);
//    repository.updateGAT(modGrastr);
//  }

  private void toastMaker(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }


  static Intent gradeAssignmentIntentFactory(Context context, int userId) {
    Intent intent = new Intent(context, GradeAssignmentActivity.class);
    intent.putExtra(GRADE_ASSIGN_ACTIVITY_USER_ID, userId);
    return intent;
  }
}