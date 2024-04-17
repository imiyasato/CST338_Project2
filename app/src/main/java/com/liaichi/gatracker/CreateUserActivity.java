package com.liaichi.gatracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.liaichi.gatracker.database.GATRepository;
import com.liaichi.gatracker.database.entities.User;
import com.liaichi.gatracker.databinding.ActivityCreateUserBinding;
import com.liaichi.gatracker.databinding.ActivityLoginPageBinding;

public class CreateUserActivity extends AppCompatActivity {
  private ActivityCreateUserBinding binding;

  private GATRepository repository;

  private String username;
  private String password;
  private String passAgain;
  private boolean isTeacher = false;
  private boolean isAdmin = false;

  RadioButton studentRadio;
  RadioButton teacherRadio;
  RadioButton adminRadio;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityCreateUserBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    repository = GATRepository.getRepository(getApplication());
    studentRadio = binding.studentRadioButton;
    teacherRadio = binding.teacherRadioButton;
    adminRadio = binding.adminRadioButton;

    binding.createUserButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        getInformationFromDisplay();
        insertNewUser();
      }
    });

  }

  private void getInformationFromDisplay() {
    this.username = binding.userCreateEditText.getText().toString();
    this.password = binding.passwordCreateEditText.getText().toString();
    this.passAgain = binding.passwordAgainEditText.getText().toString();
  }

  private void insertNewUser() {
    if(!studentRadio.isChecked() && !teacherRadio.isChecked() && !adminRadio.isChecked()){
      toastMaker("None of the user type radio buttons are checked");
      return;
    }
    if (this.username.isEmpty()) {
      toastMaker("Username may not be blank");
      return;
    }

    if(!this.password.equals(this.passAgain) || this.password.isEmpty()){
      toastMaker("There is an issue with the passwords");
      return;
    }

    User createdUser = new User(this.username, this.password);
    if(teacherRadio.isChecked()) {
      createdUser.setTeacher(true);
    } else if (adminRadio.isChecked()) {
      createdUser.setAdmin(true);
      createdUser.setTeacher(true);
    }
    toastMaker("User Creation Successful");
    repository.insertUser(createdUser);

  }

  private void toastMaker(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }

  static Intent createUserIntentFactory(Context context) {
    return new Intent(context, CreateUserActivity.class);
  }
}