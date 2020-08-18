package com.mobile.shaadidotcom.ankhiya.repository.network;

import com.mobile.shaadidotcom.ankhiya.model.Candidate;
import com.mobile.shaadidotcom.ankhiya.model.PagedData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Candidates list api class
 */
public interface CandidatesApi {

    @GET("api")
    Call<PagedData<Candidate>> getCandidates(@Query("results") int takeCount,
                                             @Query("page") int page);
}
