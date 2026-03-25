package com.example.sleepwell;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class DashboardFragment extends Fragment {

    private TextView tvGreeting, tvTotalEntries, tvAvgDuration, tvGoalStatus;
    private ProgressBar progressGoal;
    private SleepViewModel sleepViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // Find views
        tvGreeting = view.findViewById(R.id.tv_greeting);
        tvTotalEntries = view.findViewById(R.id.tv_total_entries);
        tvAvgDuration = view.findViewById(R.id.tv_avg_duration);
        tvGoalStatus = view.findViewById(R.id.tv_goal_status);
        progressGoal = view.findViewById(R.id.progress_goal);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize ViewModel
        sleepViewModel = new ViewModelProvider(requireActivity()).get(SleepViewModel.class);

        // Load user name from SharedPreferences
        SharedPreferences prefs = requireActivity().getSharedPreferences(
                "com.example.sleepwell.prefs", getContext().MODE_PRIVATE);
        String name = prefs.getString("user_name", "");
        if (!name.isEmpty()) {
            tvGreeting.setText("Welcome back, " + name + "!");
        }

        float sleepGoal = prefs.getFloat("sleep_goal", 8.0f);

        // Observe total entries
        sleepViewModel.getTotalEntries().observe(getViewLifecycleOwner(), total -> {
            if (total != null) {
                tvTotalEntries.setText(String.valueOf(total));
            }
        });

        // Observe average duration
        sleepViewModel.getAverageDuration().observe(getViewLifecycleOwner(), avg -> {
            if (avg != null) {
                tvAvgDuration.setText(String.format("%.1f", avg));

                // Update goal progress
                int progress = (int) ((avg / sleepGoal) * 100);
                if (progress > 100) progress = 100;
                progressGoal.setProgress(progress);
                tvGoalStatus.setText(String.format("%.1f / %.1f hrs (%.0f%%)",
                        avg, sleepGoal, (avg / sleepGoal) * 100));
            } else {
                tvAvgDuration.setText("0.0");
                progressGoal.setProgress(0);
                tvGoalStatus.setText("No data yet — log your first sleep!");
            }
        });
    }
}