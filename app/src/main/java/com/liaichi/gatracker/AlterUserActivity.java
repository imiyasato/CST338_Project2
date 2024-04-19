package com.liaichi.gatracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.liaichi.gatracker.database.GATRepository;
import com.liaichi.gatracker.databinding.ActivityAlterUserBinding;

public class AlterUserActivity extends AppCompatActivity {

  private ActivityAlterUserBinding binding;
  GATRepository repository;


  private String userToAlter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityAlterUserBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    repository = GATRepository.getRepository(getApplication());

    binding.deleteUserButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        userToAlter = binding.alterUserEditText.getText().toString();
        if (userToAlter.isEmpty()){
          toastMaker("user choice may not be empty");
          return;
        }
        repository.deleteUser(repository.getnlUserByUserName(userToAlter));
        toastMaker("user successfully deleted");
      }
    });
  }

  private void getUserChoice() {
    userToAlter = binding.alterUserEditText.getText().toString();
    if(userToAlter.isEmpty())
    {
      toastMaker("user choice may not be empty");
      return;
    }

  }


  private void toastMaker(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }

  static Intent alterUserIntentFactory(Context context) {
    Intent intent = new Intent(context, AlterUserActivity.class);
    return intent;
  }
}