package com.example.sleepwell;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class SettingsActivity extends AppCompatActivity {

    private EditText editName, editSleepGoal;
    private Switch switchReminder;
    private Button btnSaveSettings, btnDeleteAll;
    private SharedPreferences sharedPreferences;
    private SleepViewModel sleepViewModel;

    private static final String PREFS_FILE = "com.example.sleepwell.prefs";
    private static final String KEY_NAME = "user_name";
    private static final String KEY_SLEEP_GOAL = "sleep_goal";
    private static final String KEY_REMINDER = "reminder_enabled";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Find views
        editName = findViewById(R.id.edit_name);
        editSleepGoal = findViewById(R.id.edit_sleep_goal);
        switchReminder = findViewById(R.id.switch_reminder);
        btnSaveSettings = findViewById(R.id.btn_save_settings);
        btnDeleteAll = findViewById(R.id.btn_delete_all);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);

        // Initialize ViewModel
        sleepViewModel = new ViewModelProvider(this).get(SleepViewModel.class);

        // Load saved settings
        loadSettings();

        // Save button
        btnSaveSettings.setOnClickListener(v -> saveSettings());

        // Delete all button with confirmation dialog
        btnDeleteAll.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Delete All Data")
                    .setMessage("Are you sure? This cannot be undone.")
                    .setPositiveButton("Delete", (dialog, which) -> {
                        sleepViewModel.deleteAll();
                        Toast.makeText(this, "All sleep data deleted", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }

    private void loadSettings() {
        String name = sharedPreferences.getString(KEY_NAME, "");
        float goal = sharedPreferences.getFloat(KEY_SLEEP_GOAL, 8.0f);
        boolean reminder = sharedPreferences.getBoolean(KEY_REMINDER, false);

        editName.setText(name);
        editSleepGoal.setText(String.valueOf(goal));
        switchReminder.setChecked(reminder);
    }

    private void saveSettings() {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_NAME, editName.getText().toString().trim());

        String goalStr = editSleepGoal.getText().toString().trim();
        if (!goalStr.isEmpty()) {
            editor.putFloat(KEY_SLEEP_GOAL, Float.parseFloat(goalStr));
        }

        editor.putBoolean(KEY_REMINDER, switchReminder.isChecked());

        editor.apply();

        Toast.makeText(this, "Settings saved!", Toast.LENGTH_SHORT).show();
        finish();
    }
}