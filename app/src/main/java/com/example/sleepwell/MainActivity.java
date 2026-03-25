package com.example.sleepwell;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnAddSleep, btnHistory, btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find buttons
        btnAddSleep = findViewById(R.id.btn_add_sleep);
        btnHistory = findViewById(R.id.btn_history);
        btnSettings = findViewById(R.id.btn_settings);

        // Explicit Intents to navigate to other Activities
        btnAddSleep.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddSleepActivity.class);
            startActivity(intent);
        });

        btnHistory.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);
        });

        btnSettings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload fragment every time we come back so greeting updates
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new DashboardFragment())
                .commit();
    }
}