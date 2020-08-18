package com.mobile.shaadidotcom.ankhiya.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mobile.shaadidotcom.ankhiya.application.BaseApp;
import com.mobile.shaadidotcom.ankhiya.model.Candidate;
import com.mobile.shaadidotcom.ankhiya.repository.CandidateRepository;

import java.util.List;

/**
 * Candidate view model
 */
public class CandidatesViewModel extends AndroidViewModel {

    public static final int TAKE_COUNT = 10;
    private final CandidateRepository mRepository;
    private final LiveData<List<Candidate>> mCandidates;

    /**
     * Constructor
     * @param application Android application
     */
    public CandidatesViewModel(@NonNull Application application) {
        super(application);
        mRepository = ((BaseApp) application).getRepository();

        mCandidates = mRepository.getCandidates();
    }

    /**
     * Call this method to load data
     * @param size already loaded data size, for skip
     * @param isConnected true if connected to network, false otherwise
     */
    public void loadDataFor(int size, boolean isConnected) {
        mRepository.loadCandidates(TAKE_COUNT, size, isConnected);
    }

    /**
     * Call this method when member invitation accepted
     * @param candidate member data
     */
    public void accepted(Candidate candidate) {
        candidate.setReacted(true);
        candidate.setAccepted(true);
        mRepository.updateCandidateInDB(candidate);
    }

    /**
     * Call this method when member invitation declined
     * @param candidate member data
     */
    public void declined(Candidate candidate) {
        candidate.setReacted(true);
        candidate.setAccepted(false);
        mRepository.updateCandidateInDB(candidate);
    }

    /**
     * Get error live data
     * @return error live data
     */
    public LiveData<String> getError() {
        return mRepository.getError();
    }

    /**
     * Get data list
     * @return live data
     */
    public LiveData<List<Candidate>> getCandidates() {
        return mCandidates;
    }
}
