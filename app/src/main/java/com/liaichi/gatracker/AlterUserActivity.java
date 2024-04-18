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
import com.liaichi.gatracker.databinding.ActivityAlterUserBinding;

public class AlterUserActivity extends AppCompatActivity {
  private ActivityAlterUserBinding binding;
  GATRepository repository;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityAlterUserBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    repository = GATRepository.getRepository(getApplication());
  }

  static Intent alterUserIntentFactory(Context context) {
    Intent intent = new Intent(context, AlterUserActivity.class);
    return intent;
  }
}