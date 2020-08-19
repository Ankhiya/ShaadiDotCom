package com.mobile.shaadidotcom.ankhiya.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.mobile.shaadidotcom.ankhiya.application.AppExecutors;
import com.mobile.shaadidotcom.ankhiya.model.Candidate;
import com.mobile.shaadidotcom.ankhiya.model.PagedData;
import com.mobile.shaadidotcom.ankhiya.repository.db.AppDatabase;
import com.mobile.shaadidotcom.ankhiya.repository.db.CandidateDB;
import com.mobile.shaadidotcom.ankhiya.repository.network.CandidatesApi;
import com.mobile.shaadidotcom.ankhiya.repository.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CandidateRepository {

    private static CandidateRepository sInstance;

    private final AppDatabase mDatabase;
    private final AppExecutors mAppExecutors;
    private final CandidatesApi mCandidateApi;
    private final MutableLiveData<String> mError;
    private final MutableLiveData<List<Candidate>> mCandidates;

    private CandidateRepository(final AppDatabase appDatabase, final AppExecutors appExecutors) {
        mDatabase = appDatabase;
        mAppExecutors = appExecutors;
        mCandidateApi = RetrofitClient.createService(CandidatesApi.class);
        mCandidates = new MutableLiveData<>();
        mError = new MutableLiveData<>();
    }

    public static CandidateRepository getInstance(final AppDatabase appDatabase,
                                                  final AppExecutors appExecutors) {
        if (sInstance == null) {
            synchronized (CandidateRepository.class) {
                if (sInstance == null) {
                    sInstance = new CandidateRepository(appDatabase, appExecutors);
                }
            }
        }
        return sInstance;
    }

    public LiveData<String> getError() {
        return mError;
    }

    public LiveData<List<Candidate>> getCandidates() {
        return mCandidates;
    }

    public void updateCandidateInDB(@NonNull final Candidate candidate) {
        String gson = new Gson().toJson(candidate);
        mAppExecutors.diskIO().execute(() -> {
            mDatabase.candidateDao().update(candidate.getLoginUUID(), gson);
        });
    }

    public void loadCandidates(int take, int skip, boolean isConnected) {
        if (isConnected) {
            loadCandidatesFromNetwork((skip / take) + 1, take);
        } else {
            loadCandidatesFromDB(skip, take);
        }
    }

    private void loadCandidatesFromDB(int skip, int take) {
        mAppExecutors.diskIO().execute(() -> {
            List<CandidateDB> candidatesDBList = mDatabase.candidateDao().getCandidates(skip, take);
            List<Candidate> candidates = new ArrayList<>();
            for (CandidateDB candidateDB : candidatesDBList) {
                Candidate candidate = new Gson().fromJson(candidateDB.getJson(),
                        Candidate.class);
                candidates.add(candidate);
            }
            mCandidates.postValue(candidates);
        });
    }

    private void loadCandidatesFromNetwork(int page, int take) {
        mCandidateApi.getCandidates(take, page)
                .enqueue(new Callback<PagedData<Candidate>>() {

                    @Override
                    public void onResponse(@NonNull Call<PagedData<Candidate>> call,
                                           @NonNull Response<PagedData<Candidate>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            getDBStatus(response.body().getResults());
                            saveCandidatesInDB(response.body());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<PagedData<Candidate>> call,
                                          @NonNull Throwable t) {
                        mError.postValue(t.getMessage());
                    }
                });
    }

    private void getDBStatus(@NonNull List<Candidate> candidates) {
        mAppExecutors.diskIO().execute(() -> {
            for (Candidate candidate : candidates) {
                CandidateDB candidateDB = mDatabase.candidateDao()
                        .getCandidate(candidate.getLoginUUID());
                if (candidateDB != null) {
                    Candidate dbCandidate = new Gson().fromJson(candidateDB.getJson(), Candidate.class);
                    candidate.setReacted(dbCandidate.isReacted());
                    candidate.setAccepted(dbCandidate.isAccepted());
                }
            }
            mCandidates.postValue(candidates);
        });
    }

    private void saveCandidatesInDB(@NonNull final PagedData<Candidate> pagedData) {
        List<CandidateDB> candidateDBS = new ArrayList<>();
        for (Candidate candidate : pagedData.getResults()) {
            String gson = new Gson().toJson(candidate);
            CandidateDB candidateDB = new CandidateDB(candidate.getLoginUUID(), gson);
            candidateDBS.add(candidateDB);
        }

        mAppExecutors.diskIO().execute(() -> {
            // as data is not coming with same order all time
            if (pagedData.getPageNumber() == 1) {
                mDatabase.candidateDao().deleteAll();
            }
            mDatabase.candidateDao().insertAll(candidateDBS);
        });

    }
}
