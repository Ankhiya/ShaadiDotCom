package com.mobile.shaadidotcom.ankhiya.application;

import android.app.Application;

import com.mobile.shaadidotcom.ankhiya.repository.CandidateRepository;
import com.mobile.shaadidotcom.ankhiya.repository.db.AppDatabase;
import com.mobile.shaadidotcom.ankhiya.repository.network.RetrofitClient;

import retrofit2.Retrofit;

/**
 * Android application class. Used for accessing singletons
 */
public class BaseApp extends Application {

    private AppExecutors mExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

        mExecutors = new AppExecutors();
    }

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this);
    }

    public CandidateRepository getRepository() {
        return CandidateRepository.getInstance(getDatabase(), mExecutors);
    }

    public Retrofit getRetrofit() {
        return RetrofitClient.getInstance();
    }
}
