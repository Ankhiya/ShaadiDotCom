package com.mobile.shaadidotcom.ankhiya.model.candidate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mobile.shaadidotcom.ankhiya.utils.StringUtils;

/**
 * model class for Street
 */
public class Street {

    @SerializedName("number")
    @Expose
    private String mNumber;

    @SerializedName("name")
    @Expose
    private String mName;

    public String getNumber() {
        return StringUtils.nonNullString(mNumber);
    }

    public String getName() {
        return StringUtils.nonNullString(mName);
    }
}
