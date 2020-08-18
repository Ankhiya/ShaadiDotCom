package com.mobile.shaadidotcom.ankhiya.model.candidate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mobile.shaadidotcom.ankhiya.utils.StringUtils;

public class DateDetails {

    @SerializedName("date")
    @Expose
    private String mDate;

    @SerializedName("age")
    @Expose
    private int mAge;

    public String getDate() {
        return StringUtils.nonNullString(mDate);
    }

    public int getAge() {
        return mAge;
    }
}
