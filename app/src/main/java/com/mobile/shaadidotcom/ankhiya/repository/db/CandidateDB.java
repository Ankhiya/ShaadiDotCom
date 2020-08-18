package com.mobile.shaadidotcom.ankhiya.repository.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.Gson;
import com.mobile.shaadidotcom.ankhiya.model.Candidate;

/**
 * Entity
 */
@Entity(tableName = "candidate")
public class CandidateDB {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "uuid")
    private String mUUID;

    @ColumnInfo(name = "json")
    private String mJson;

    public CandidateDB(@NonNull String mUUID, String mJson) {
        this.mUUID = mUUID;
        this.mJson = mJson;
    }

    @NonNull
    public String getUUID() {
        return mUUID;
    }

    public String getJson() {
        return mJson;
    }

    public Candidate getCandidate() {
        Gson gson = new Gson();
        return gson.fromJson(mJson, Candidate.class);
    }
}
