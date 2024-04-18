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
import com.liaichi.gatracker.databinding.ActivityAcceptAssignmentBinding;

public class AcceptAssignmentActivity extends AppCompatActivity {

  private static final String ACCEPT_ASSIGN_ACTIVITY_USER_ID = "com.liaichi.gatracker.ACCEPT_ASSIGN_ACTIVITY_USER_ID";
  ActivityAcceptAssignmentBinding binding;
  private GATRepository repository;
  private int loggedInUserId;
  private int assignmentID;
  private String choiceAssign;

  private Assignment assignment;
  private int assignId;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityAcceptAssignmentBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    loggedInUserId = getIntent().getIntExtra(ACCEPT_ASSIGN_ACTIVITY_USER_ID, -1);
    repository = GATRepository.getRepository(getApplication());

    binding.acceptButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        getInformationFromDisplay();
        insertNewAccept();
      }
    });
  }

  private void getInformationFromDisplay() {
    this.choiceAssign = binding.enterAssignmentEditText.getText().toString();
  }

  private void insertNewAccept(){
    LiveData<Assignment> assignObserver = repository.getAssignmentByAssignmentName(this.choiceAssign);
    assignObserver.observe(this, assignment -> {
      this.assignment = assignment;
      if (this.assignment == null) {
        toastMaker("Assignment not found");
      } else {
        assignId = this.assignment.getAssignmentId();
        GrAsTr grastrLog = new GrAsTr(loggedInUserId, assignId);
        repository.insertGAT(grastrLog);
        toastMaker("Assignment accepted");
      }
    });
  }

  private void toastMaker(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }

  static Intent acceptAssignmentIntentFactory(Context context, int userId) {
    Intent intent = new Intent(context, AcceptAssignmentActivity.class);
    intent.putExtra(ACCEPT_ASSIGN_ACTIVITY_USER_ID, userId);
    return intent;
  }
}