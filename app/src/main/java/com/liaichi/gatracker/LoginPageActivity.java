package com.liaichi.gatracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import com.liaichi.gatracker.database.GATRepository;
import com.liaichi.gatracker.database.entities.User;
import com.liaichi.gatracker.databinding.ActivityLoginPageBinding;

public class LoginPageActivity extends AppCompatActivity {

  private ActivityLoginPageBinding binding;

  GATRepository repository;

  public void setRepository(GATRepository repository) {
    this.repository = repository;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityLoginPageBinding.inflate(getLayoutInflater());

    setContentView(binding.getRoot());

    repository = GATRepository.getRepository(getApplication());

    binding.loginButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        verifyUser();
      }
    });
  }


  private void verifyUser() {
    String username = binding.userNameLoginEditText.getText().toString();
    if (username.isEmpty()) {
      toastMaker("Username may not be blank");
      return;
    }
    LiveData<User> userObserver = repository.getUserByUserName(username);
    userObserver.observe(this, user -> {
      if (user != null) {
        String password = binding.passwordLoginEditText.getText().toString();
        if (password.equals(user.getPassword())) {
          startActivity(
              LandingPageActivity.landingPageActivityIntentFactory(getApplicationContext(),
                  user.getId()));
        } else {
          toastMaker("Invalid password");
          binding.passwordLoginEditText.setSelection(0);
        }
      } else {
        toastMaker(String.format("%s is not a valid username", username));
        binding.userNameLoginEditText.setSelection(0);
      }
    });
  }

  private void toastMaker(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }

  static Intent loginIntentFactory(Context context) {
    return new Intent(context, LoginPageActivity.class);
  }

}