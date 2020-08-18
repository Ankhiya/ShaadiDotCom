package com.mobile.shaadidotcom.ankhiya.model;

/**
 * Contract to be implemented by main Candidate model
 */
public interface CandidateContract {

    String getTitle();

    String getFirstName();

    String getLastName();

    String getPhoneNumber();

    String getCellNumber();

    String getEmail();

    String getState();

    String getCity();

    String getCountry();

    String getDateOfBirth();

    int getDOBAge();

    String getThumbnailPictureURL();

    String getMediumPictureURL();

    String getLargePictureURL();

    String getLoginUUID();

    boolean isAccepted();

    boolean isReacted();

    String getName();

    String getAgeNat();

    String getAddress();

    String getNat();

    void setAccepted(boolean isAccepted);

    void setReacted(boolean isReacted);
}
