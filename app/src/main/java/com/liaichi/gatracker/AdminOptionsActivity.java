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
import com.liaichi.gatracker.databinding.ActivityAdminOptionsBinding;

public class AdminOptionsActivity extends AppCompatActivity {

  ActivityAdminOptionsBinding binding;
  private GATRepository repository;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityAdminOptionsBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    repository = GATRepository.getRepository(getApplication());


    binding.createCourseButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = CreateCourseActivity.createCourseIntentFactory(getApplicationContext());
        startActivity(intent);
      }
    });

    binding.editCourseNameButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = EditCourseNameActivity.editCourseNameIntentFactory(getApplicationContext());
        startActivity(intent);
      }
    });

    binding.alterUserButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = AlterUserActivity.alterUserIntentFactory(getApplicationContext());
        startActivity(intent);
      }
    });

    binding.createUserButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = CreateUserActivity.createUserIntentFactory(getApplicationContext());
        startActivity(intent);
      }
    });


  }

  static Intent adminOptionsIntentFactory(Context context) {
    return new Intent(context, AdminOptionsActivity.class);
  }

}
