package com.example.sleepwell;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SleepAdapter extends RecyclerView.Adapter<SleepAdapter.SleepViewHolder> {

    private List<SleepEntry> sleepList = new ArrayList<>();

    public void setSleepList(List<SleepEntry> entries) {
        this.sleepList = entries;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SleepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_sleep_item, parent, false);
        return new SleepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SleepViewHolder holder, int position) {
        SleepEntry entry = sleepList.get(position);

        holder.tvDate.setText(entry.getDate());
        holder.tvDuration.setText(entry.getDuration() + " hrs");

        // Build time string
        String times = entry.getBedTime() + " → " + entry.getWakeTime();
        holder.tvTimes.setText(times);

        // Build star rating
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < entry.getQuality(); i++) {
            stars.append("★");
        }
        for (int i = entry.getQuality(); i < 5; i++) {
            stars.append("☆");
        }
        holder.tvQuality.setText(stars.toString());

        // Show note if present
        if (entry.getNote() != null && !entry.getNote().isEmpty()) {
            holder.tvNote.setText(entry.getNote());
            holder.tvNote.setVisibility(View.VISIBLE);
        } else {
            holder.tvNote.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return sleepList.size();
    }

    public SleepEntry getEntryAt(int position) {
        return sleepList.get(position);
    }

    public static class SleepViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvTimes, tvDuration, tvQuality, tvNote;

        public SleepViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvTimes = itemView.findViewById(R.id.tv_times);
            tvDuration = itemView.findViewById(R.id.tv_duration);
            tvQuality = itemView.findViewById(R.id.tv_quality);
            tvNote = itemView.findViewById(R.id.tv_note);
        }
    }
}