package com.mobile.shaadidotcom.ankhiya.model.candidate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mobile.shaadidotcom.ankhiya.utils.StringUtils;

public class Location {

    @SerializedName("street")
    @Expose
    private Street mStreet;

    @SerializedName("city")
    @Expose
    private String mCity;

    @SerializedName("state")
    @Expose
    private String mState;

    @SerializedName("country")
    @Expose
    private String mCountry;

    @SerializedName("postcode")
    @Expose
    private String mPostCode;

    @SerializedName("coordinates")
    @Expose
    private Coordinates mCoordinates;

    @SerializedName("timezone")
    @Expose
    private TimeZone mTimeZone;

    public Street getStreet() {
        return mStreet;
    }

    public String getCity() {
        return StringUtils.nonNullString(mCity);
    }

    public String getState() {
        return StringUtils.nonNullString(mState);
    }

    public String getCountry() {
        return StringUtils.nonNullString(mCountry);
    }

    public String getPostCode() {
        return mPostCode;
    }

    public Coordinates getCoordinates() {
        return mCoordinates;
    }

    public TimeZone getTimeZone() {
        return mTimeZone;
    }
}
