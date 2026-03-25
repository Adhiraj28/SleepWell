package com.example.sleepwell;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SleepRepository {

    private SleepDao sleepDao;
    private LiveData<List<SleepEntry>> allEntries;
    private LiveData<Float> averageDuration;
    private LiveData<Integer> totalEntries;

    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public SleepRepository(Application application) {
        SleepDatabase db = SleepDatabase.getDatabase(application);
        sleepDao = db.sleepDao();
        allEntries = sleepDao.getAllEntries();
        averageDuration = sleepDao.getAverageDuration();
        totalEntries = sleepDao.getTotalEntries();
    }

    public LiveData<List<SleepEntry>> getAllEntries() {
        return allEntries;
    }

    public LiveData<Float> getAverageDuration() {
        return averageDuration;
    }

    public LiveData<Integer> getTotalEntries() {
        return totalEntries;
    }

    public void insert(SleepEntry entry) {
        executor.execute(() -> sleepDao.insert(entry));
    }

    public void delete(SleepEntry entry) {
        executor.execute(() -> sleepDao.delete(entry));
    }

    public void deleteAll() {
        executor.execute(() -> sleepDao.deleteAll());
    }
}