package com.example.sleepwell;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sleep_table")
public class SleepEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "bed_time")
    private String bedTime;

    @ColumnInfo(name = "wake_time")
    private String wakeTime;

    @ColumnInfo(name = "duration")
    private float duration;

    @ColumnInfo(name = "quality")
    private int quality;

    @ColumnInfo(name = "note")
    private String note;

    // Constructor
    public SleepEntry(@NonNull String date, String bedTime, String wakeTime,
                      float duration, int quality, String note) {
        this.date = date;
        this.bedTime = bedTime;
        this.wakeTime = wakeTime;
        this.duration = duration;
        this.quality = quality;
        this.note = note;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @NonNull
    public String getDate() { return date; }
    public void setDate(@NonNull String date) { this.date = date; }

    public String getBedTime() { return bedTime; }
    public void setBedTime(String bedTime) { this.bedTime = bedTime; }

    public String getWakeTime() { return wakeTime; }
    public void setWakeTime(String wakeTime) { this.wakeTime = wakeTime; }

    public float getDuration() { return duration; }
    public void setDuration(float duration) { this.duration = duration; }

    public int getQuality() { return quality; }
    public void setQuality(int quality) { this.quality = quality; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}