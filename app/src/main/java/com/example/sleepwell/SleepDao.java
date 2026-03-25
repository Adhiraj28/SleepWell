package com.example.sleepwell;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SleepDao {

    @Insert
    void insert(SleepEntry sleepEntry);

    @Delete
    void delete(SleepEntry sleepEntry);

    @Query("DELETE FROM sleep_table")
    void deleteAll();

    @Query("SELECT * FROM sleep_table ORDER BY date DESC")
    LiveData<List<SleepEntry>> getAllEntries();

    @Query("SELECT AVG(duration) FROM sleep_table")
    LiveData<Float> getAverageDuration();

    @Query("SELECT COUNT(*) FROM sleep_table")
    LiveData<Integer> getTotalEntries();
}