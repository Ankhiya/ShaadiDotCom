package com.mobile.shaadidotcom.ankhiya.view;

import com.mobile.shaadidotcom.ankhiya.model.Candidate;

/**
 * Callback for candidate list item
 */
public interface CandidateAdapterCallback {

    /**
     * Call this to load more data
     * @param currentSize skip count
     */
    void getMoreData(int currentSize);

    /**
     * Call this method when clicked accepted
     * @param candidate member data
     */
    void acceptCandidate(Candidate candidate);

    /**
     * CAll this method when clicked declined
     * @param candidate member data
     */
    void declineCandidate(Candidate candidate);
}
