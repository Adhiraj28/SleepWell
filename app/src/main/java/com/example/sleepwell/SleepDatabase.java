package com.example.sleepwell;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {SleepEntry.class}, version = 1, exportSchema = false)
public abstract class SleepDatabase extends RoomDatabase {

    public abstract SleepDao sleepDao();

    private static volatile SleepDatabase INSTANCE;

    static SleepDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SleepDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            SleepDatabase.class,
                            "sleep_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}