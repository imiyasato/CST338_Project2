package com.liaichi.gatracker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import com.liaichi.gatracker.database.GATRepository;
import com.liaichi.gatracker.database.entities.User;
import com.liaichi.gatracker.databinding.ActivityLandingPageBinding;

public class LandingPageActivity extends AppCompatActivity {

  private static final String LANDING_ACTIVITY_USER_ID = "com.liaichi.gatracker.LANDING_ACTIVITY_USER_ID";
  static final String SAVED_INSTANCE_STATE_USERID_KEY = "com.liaichi.gatracker.SAVED_INSTANCE_STATE_USERID_KEY";
  public static final String TAG = "LI_GAT";
  private static final int LOGGED_OUT = -1;

  private ActivityLandingPageBinding binding;
  private GATRepository repository;

  private int loggedInUserId = -1;
  private User user;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityLandingPageBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    repository = GATRepository.getRepository(getApplication());

    loginUser(savedInstanceState);

    if (loggedInUserId == -1) {
      Intent intent = LoginPageActivity.loginIntentFactory(getApplicationContext());
      startActivity(intent);
    }
    updateSharedPreference();

    binding.acceptAssignmentsButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = AcceptAssignmentActivity.acceptAssignmentIntentFactory(
            getApplicationContext(), loggedInUserId);
        startActivity(intent);
      }
    });

    binding.viewGradesButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = ViewGradesActivity.viewGradesIntentFactory(getApplicationContext(), loggedInUserId);
        startActivity(intent);
      }
    });

    binding.teacherOptionsButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = TeacherOptionsActivity.teacherOptionsIntentFactory(
            getApplicationContext(), loggedInUserId);
        startActivity(intent);
      }
    });

    binding.adminOptionsButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = AdminOptionsActivity.adminOptionsIntentFactory(
            getApplicationContext());
        startActivity(intent);
      }
    });

  }


  private void loginUser(Bundle savedInstanceState) {
    // check shared preference for logged in user
    SharedPreferences sharedPreferences = getSharedPreferences(
        getString(R.string.preference_file_key), Context.MODE_PRIVATE);

    loggedInUserId = sharedPreferences.getInt(getString(R.string.preference_userid_key),
        LOGGED_OUT);

    if (loggedInUserId == LOGGED_OUT & savedInstanceState != null && savedInstanceState.containsKey(
        SAVED_INSTANCE_STATE_USERID_KEY)) {
      loggedInUserId = savedInstanceState.getInt(SAVED_INSTANCE_STATE_USERID_KEY, LOGGED_OUT);
    }

    if (loggedInUserId == LOGGED_OUT) {
      loggedInUserId = getIntent().getIntExtra(LANDING_ACTIVITY_USER_ID, LOGGED_OUT);
    }

    if (loggedInUserId == LOGGED_OUT) {
      return;
    }

    LiveData<User> userObserver = repository.getUserByUserId(loggedInUserId);
    userObserver.observe(this, user -> {
      this.user = user;
      if (this.user != null) {
        invalidateOptionsMenu();
        if (!user.isAdmin()) {
          binding.adminOptionsButton.setVisibility(View.GONE);
          if (!user.isTeacher()) {
            binding.teacherOptionsButton.setVisibility(View.GONE);
          }
        } else {
          binding.welcomeUserTextView.setText(R.string.welcome_admin);
        }
      }
    });
  }


  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt(SAVED_INSTANCE_STATE_USERID_KEY, loggedInUserId);
    updateSharedPreference();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.logout_menu, menu);

    return true;
  }

  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {
    MenuItem item = menu.findItem(R.id.logoutMenuItem);
    item.setVisible(true);
    if (user == null) {
      return false;
    }

    item.setTitle(user.getUsername());
    item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(@NonNull MenuItem item) {
        showLogoutDialog();
        return false;
      }
    });
    return true;
  }

  private void showLogoutDialog() {
    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(LandingPageActivity.this);
    final AlertDialog alertDialog = alertBuilder.create();

    alertBuilder.setMessage("Logout?");
    alertBuilder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        logout();
      }
    });

    alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        alertDialog.dismiss();
      }
    });

    alertBuilder.create().show();
  }

  private void logout() {
    loggedInUserId = LOGGED_OUT;
    updateSharedPreference();
    getIntent().putExtra(LANDING_ACTIVITY_USER_ID, loggedInUserId);
    startActivity(LoginPageActivity.loginIntentFactory(getApplicationContext()));
  }

  private void updateSharedPreference() {
    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(
        getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
    sharedPrefEditor.putInt(getString(R.string.preference_userid_key), loggedInUserId);
    sharedPrefEditor.apply();
  }


  static Intent landingPageActivityIntentFactory(Context context, int userId) {
    Intent intent = new Intent(context, LandingPageActivity.class);
    intent.putExtra(LANDING_ACTIVITY_USER_ID, userId);
    return intent;
  }
}