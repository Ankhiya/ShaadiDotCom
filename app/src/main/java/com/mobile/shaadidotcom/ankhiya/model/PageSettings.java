package com.mobile.shaadidotcom.ankhiya.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Page settings
 */
public class PageSettings {

    @SerializedName("page")
    @Expose
    private int mPage;

    @SerializedName("results")
    @Expose
    private int mResults;

    @SerializedName("seed")
    @Expose
    private String mSeed;

    @SerializedName("version")
    @Expose
    private String mVersion;

    public PageSettings() {
    }

    int getPage() {
        return mPage;
    }

    int getResults() {
        return mResults;
    }

    String getSeed() {
        return mSeed;
    }

    String getVersion() {
        return mVersion;
    }
}
