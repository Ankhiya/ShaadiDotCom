package com.mobile.shaadidotcom.ankhiya.repository.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 * Candidate dao
 * insert, update, delete, create operations for {@link CandidateDB}
 */
@Dao
public interface CandidateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(CandidateDB candidateDB);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CandidateDB> candidateDBList);

    @Query("DELETE FROM candidate")
    void deleteAll();

    @Query("UPDATE candidate SET json = :candidateJson WHERE uuid = :loginId")
    void update(String loginId, String candidateJson);

    @Query("SELECT * FROM candidate LIMIT :skip, :take")
    List<CandidateDB> getCandidates(int skip, int take);
}
