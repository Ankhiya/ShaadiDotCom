package com.mobile.shaadidotcom.ankhiya.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PagedData<T> implements PagedDataContract<T> {

    @SerializedName("results")
    @Expose
    private List<T> mResults;

    @SerializedName("info")
    @Expose
    private PageSettings mInfo;

    public PagedData() {
    }

    @Override
    public int size() {
        return mResults != null ? mResults.size() : 0;
    }

    @Override
    public boolean isEmpty() {
        return mResults == null || mResults.isEmpty();
    }

    @Override
    public List<T> getResults() {
        return mResults;
    }

    @Override
    public String getSeed() {
        return mInfo != null ? mInfo.getSeed() : null;
    }

    @Override
    public int getTakeCount() {
        return mInfo != null ? mInfo.getResults() : 0;
    }

    @Override
    public int getPageNumber() {
        return mInfo != null ? mInfo.getPage() : 0;
    }

    @Override
    public String getAPIVersion() {
        return mInfo != null ? mInfo.getVersion() : null;
    }
}
