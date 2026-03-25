package com.example.sleepwell;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class SleepViewModel extends AndroidViewModel {

    private SleepRepository repository;
    private LiveData<List<SleepEntry>> allEntries;
    private LiveData<Float> averageDuration;
    private LiveData<Integer> totalEntries;

    public SleepViewModel(@NonNull Application application) {
        super(application);
        repository = new SleepRepository(application);
        allEntries = repository.getAllEntries();
        averageDuration = repository.getAverageDuration();
        totalEntries = repository.getTotalEntries();
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
        repository.insert(entry);
    }

    public void delete(SleepEntry entry) {
        repository.delete(entry);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}