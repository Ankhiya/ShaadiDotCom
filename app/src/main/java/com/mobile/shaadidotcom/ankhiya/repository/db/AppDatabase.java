package com.mobile.shaadidotcom.ankhiya.repository.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mobile.shaadidotcom.ankhiya.BuildConfig;

/**
 * App database
 */
@Database(entities = {CandidateDB.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sInstance;

    public abstract CandidateDao candidateDao();

    public static AppDatabase getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, BuildConfig.DB_NAME).build();
                }
            }
        }
        return sInstance;
    }
}
