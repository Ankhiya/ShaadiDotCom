package com.mobile.shaadidotcom.ankhiya.model.candidate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mobile.shaadidotcom.ankhiya.utils.StringUtils;

public class LoginDetails {

    @SerializedName("uuid")
    @Expose
    private String mUUID;

    @SerializedName("username")
    @Expose
    private String mUserName;

    @SerializedName("password")
    @Expose
    private String mPassword;

    @SerializedName("salt")
    @Expose
    private String mSalt;

    @SerializedName("md5")
    @Expose
    private String mMD5;

    @SerializedName("sha1")
    @Expose
    private String mSHA1;

    @SerializedName("sha256")
    private String mSHA256;

    public String getUUID() {
        return StringUtils.nonNullString(mUUID);
    }

    public String getUserName() {
        return StringUtils.nonNullString(mUserName);
    }

    public String getPassword() {
        return StringUtils.nonNullString(mPassword);
    }

    public String getSalt() {
        return StringUtils.nonNullString(mSalt);
    }

    public String getMD5() {
        return StringUtils.nonNullString(mMD5);
    }

    public String getSHA1() {
        return StringUtils.nonNullString(mSHA1);
    }

    public String getSHA256() {
        return StringUtils.nonNullString(mSHA256);
    }
}
