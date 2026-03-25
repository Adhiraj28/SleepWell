package com.example.sleepwell;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class AddSleepActivity extends AppCompatActivity {

    private EditText editDate, editBedTime, editWakeTime, editDuration, editQuality, editNote;
    private Button btnSave;
    private SleepViewModel sleepViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sleep);

        // Find views by ID
        editDate = findViewById(R.id.edit_date);
        editBedTime = findViewById(R.id.edit_bed_time);
        editWakeTime = findViewById(R.id.edit_wake_time);
        editDuration = findViewById(R.id.edit_duration);
        editQuality = findViewById(R.id.edit_quality);
        editNote = findViewById(R.id.edit_note);
        btnSave = findViewById(R.id.btn_save);

        // Initialize ViewModel
        sleepViewModel = new ViewModelProvider(this).get(SleepViewModel.class);

        // Set onClickListener for save button
        btnSave.setOnClickListener(v -> saveSleepEntry());
    }

    private void saveSleepEntry() {
        String date = editDate.getText().toString().trim();
        String bedTime = editBedTime.getText().toString().trim();
        String wakeTime = editWakeTime.getText().toString().trim();
        String durationStr = editDuration.getText().toString().trim();
        String qualityStr = editQuality.getText().toString().trim();
        String note = editNote.getText().toString().trim();

        // Validate required fields
        if (date.isEmpty() || durationStr.isEmpty() || qualityStr.isEmpty()) {
            Toast.makeText(this, "Please fill in date, hours, and quality", Toast.LENGTH_SHORT).show();
            return;
        }

        float duration = Float.parseFloat(durationStr);
        int quality = Integer.parseInt(qualityStr);

        // Validate quality range
        if (quality < 1 || quality > 5) {
            Toast.makeText(this, "Quality must be between 1 and 5", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create and insert entry
        SleepEntry entry = new SleepEntry(date, bedTime, wakeTime, duration, quality, note);
        sleepViewModel.insert(entry);

        Toast.makeText(this, "Sleep entry saved!", Toast.LENGTH_SHORT).show();
        finish(); // Go back to previous screen
    }
}