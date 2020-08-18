package com.mobile.shaadidotcom.ankhiya.model.candidate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mobile.shaadidotcom.ankhiya.utils.StringUtils;

/**
 * model class for Name
 */
public class Name {

    @SerializedName("title")
    @Expose
    private String mTitle;

    @SerializedName("first")
    @Expose
    private String mFirst;

    @SerializedName("last")
    @Expose
    private String mLast;

    public String getTitle() {
        return StringUtils.nonNullString(mTitle);
    }

    public String getFirst() {
        return StringUtils.nonNullString(mFirst);
    }

    public String getLast() {
        return StringUtils.nonNullString(mLast);
    }
}
