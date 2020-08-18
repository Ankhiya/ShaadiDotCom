package com.mobile.shaadidotcom.ankhiya.model.candidate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mobile.shaadidotcom.ankhiya.utils.StringUtils;

/**
 * model class for TimeZone
 */
public class TimeZone {

    @SerializedName("offset")
    @Expose
    private String mOffset;

    @SerializedName("description")
    @Expose
    private String mDescription;

    public String getOffset() {
        return StringUtils.nonNullString(mOffset);
    }

    public String getDescription() {
        return StringUtils.nonNullString(mDescription);
    }
}
