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
import com.liaichi.gatracker.databinding.ActivityEditCourseNameBinding;

public class EditCourseNameActivity extends AppCompatActivity {

  private ActivityEditCourseNameBinding binding;
  GATRepository repository;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityEditCourseNameBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    repository = GATRepository.getRepository(getApplication());
  }

  static Intent editCourseNameIntentFactory(Context context) {
    Intent intent = new Intent(context, EditCourseNameActivity.class);
    return intent;
  }
}