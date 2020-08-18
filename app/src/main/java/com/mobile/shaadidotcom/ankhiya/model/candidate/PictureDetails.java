package com.mobile.shaadidotcom.ankhiya.model.candidate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * model class for Picture details
 */
public class PictureDetails {

    @SerializedName("large")
    @Expose
    private String mLarge;

    @SerializedName("medium")
    @Expose
    private String mMedium;

    @SerializedName("thumbnail")
    @Expose
    private String mThumbnail;

    public String getLarge() {
        return mLarge;
    }

    public String getMedium() {
        return mMedium;
    }

    public String getThumbnail() {
        return mThumbnail;
    }
}
