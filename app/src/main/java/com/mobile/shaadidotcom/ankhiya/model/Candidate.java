package com.mobile.shaadidotcom.ankhiya.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mobile.shaadidotcom.ankhiya.model.candidate.DateDetails;
import com.mobile.shaadidotcom.ankhiya.model.candidate.Location;
import com.mobile.shaadidotcom.ankhiya.model.candidate.LoginDetails;
import com.mobile.shaadidotcom.ankhiya.model.candidate.Name;
import com.mobile.shaadidotcom.ankhiya.model.candidate.PictureDetails;
import com.mobile.shaadidotcom.ankhiya.utils.StringUtils;

import static com.mobile.shaadidotcom.ankhiya.utils.StringUtils.NA;

/**
 * Implementation for Candidate interface
 */
public class Candidate  implements CandidateContract {

    @SerializedName("gender")
    @Expose
    private String mGender;

    @SerializedName("name")
    @Expose
    private Name mName;

    @SerializedName("location")
    @Expose
    private Location mLocation;

    @SerializedName("email")
    @Expose
    private String mEmail;

    @SerializedName("login")
    @Expose
    private LoginDetails mLogin;

    @SerializedName("phone")
    @Expose
    private String mPhone;

    @SerializedName("cell")
    @Expose
    private String mCell;

    @SerializedName("picture")
    @Expose
    private PictureDetails mPicture;

    @SerializedName("dob")
    @Expose
    private DateDetails mDOB;

    @SerializedName("registered")
    @Expose
    private DateDetails mRegisteredDate;

    @SerializedName("nat")
    @Expose
    private String mNat;

    private boolean mAccepted;

    private boolean mReacted;

    public Candidate() {
    }

    @Override
    public String getTitle() {
        return mName != null ? mName.getTitle() : NA;
    }

    @Override
    public String getFirstName() {
        return mName != null ? mName.getFirst() : NA;
    }

    @Override
    public String getLastName() {
        return mName != null ? mName.getLast() : NA;
    }

    @Override
    public String getPhoneNumber() {
        return StringUtils.nonNullString(mPhone);
    }

    @Override
    public String getCellNumber() {
        return StringUtils.nonNullString(mCell);
    }

    @Override
    public String getEmail() {
        return StringUtils.nonNullString(mEmail);
    }

    @Override
    public String getState() {
        return mLocation != null ? mLocation.getState() : NA;
    }

    @Override
    public String getCity() {
        return mLocation != null ? mLocation.getCity() : NA;
    }

    @Override
    public String getCountry() {
        return mLocation != null ? mLocation.getCountry() : NA;
    }

    @Override
    public String getDateOfBirth() {
        return mDOB != null ? mDOB.getDate() : null;
    }

    @Override
    public int getDOBAge() {
        return mDOB != null ? mDOB.getAge() : 0;
    }

    @Override
    public String getThumbnailPictureURL() {
        return mPicture != null ? mPicture.getThumbnail() : null;
    }

    @Override
    public String getMediumPictureURL() {
        return mPicture != null ? mPicture.getMedium() : null;
    }

    @Override
    public String getLargePictureURL() {
        return mPicture != null ? mPicture.getLarge() : null;
    }

    @Override
    public String getLoginUUID() {
        return mLogin != null ? mLogin.getUUID() : null;
    }

    @Override
    public boolean isAccepted() {
        return mAccepted;
    }

    @Override
    public boolean isReacted() {
        return mReacted;
    }

    @Override
    public String getName() {
        return String.format("%s %s", getFirstName(), getLastName());
    }

    @Override
    public String getAgeNat() {
        return String.format("%d, %s", getDOBAge(), getNat());
    }

    @Override
    public String getAddress() {
        return String.format("%s, %s, %s", getCity(), getState(), getCountry());
    }

    @Override
    public String getNat() {
        return StringUtils.nonNullString(mNat);
    }

    @Override
    public void setAccepted(boolean isAccepted) {
        mAccepted = isAccepted;
    }

    @Override
    public void setReacted(boolean isReacted) {
        mReacted = isReacted;
    }
}
