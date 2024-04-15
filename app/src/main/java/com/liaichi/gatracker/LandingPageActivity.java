package com.liaichi.gatracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.liaichi.gatracker.database.GATRepository;
import com.liaichi.gatracker.databinding.ActivityLandingPageBinding;

public class LandingPageActivity extends AppCompatActivity {
  private ActivityLandingPageBinding binding;
  private GATRepository repository;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityLandingPageBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    repository = GATRepository.getRepository(getApplication());

  }


  static Intent landingPageActivityIntentFactory(Context context, int userID) {
    Intent intent = new Intent(context, LandingPageActivity.class);
    intent.putExtra(MAIN_ACTIVITY_USER_ID, userID);
    return intent;
  }
}