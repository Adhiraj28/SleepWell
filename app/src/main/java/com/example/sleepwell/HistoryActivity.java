package com.example.sleepwell;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryActivity extends AppCompatActivity {

    private SleepViewModel sleepViewModel;
    private SleepAdapter adapter;
    private RecyclerView recyclerView;
    private TextView tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Initialize views
        recyclerView = findViewById(R.id.recycler_view);
        tvEmpty = findViewById(R.id.tv_empty);

        // Setup RecyclerView with LinearLayoutManager
        adapter = new SleepAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize ViewModel and observe data
        sleepViewModel = new ViewModelProvider(this).get(SleepViewModel.class);
        sleepViewModel.getAllEntries().observe(this, entries -> {
            if (entries != null && !entries.isEmpty()) {
                adapter.setSleepList(entries);
                recyclerView.setVisibility(View.VISIBLE);
                tvEmpty.setVisibility(View.GONE);
            } else {
                recyclerView.setVisibility(View.GONE);
                tvEmpty.setVisibility(View.VISIBLE);
            }
        });
    }
}