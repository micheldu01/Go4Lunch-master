package com.example.michel.go4lunch.APIMaps.apiPlaceId;


import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultAPI {

    // DECLARE ADDRESS COMPONENTS
    @SerializedName("address_components")
    @Expose
    private List<AddressComponent> addressComponents;

    // DECLARE STRING  WEBSITE
    @SerializedName("website")
    @Expose
    public String website;

    // DECLARE PHONE
    @SerializedName("international_phone_number")
    @Expose
    public String phone;

    // DECLARE LIST OPEN (hours)
    @SerializedName("opening_hours")
    @Expose
    @Nullable
    public OpeningHours opening_hours;

    // DECLARE PHOTO
    @SerializedName("photos")
    @Expose
    @Nullable
    public List<Photo> photos = null;


    // CONTRUCTOR WITHOUT OPENING HOURS


    public ResultAPI(List<AddressComponent> addressComponents, String website, String phone, List<Photo> photos) {
        this.addressComponents = addressComponents;
        this.website = website;
        this.phone = phone;
        this.photos = photos;
    }

    // CONSTRUCTOR
    public ResultAPI(List<AddressComponent> addressComponents, String website, String phone, OpeningHours opening_hours, List<Photo> photos) {
        this.addressComponents = addressComponents;
        this.website = website;
        this.phone = phone;
        this.opening_hours = opening_hours;
        this.photos = photos;
    }


    // GETTER AND SETTER

    public List<AddressComponent> getAddressComponents() {
        return addressComponents;
    }

    public void setAddressComponents(List<AddressComponent> addressComponents) {
        this.addressComponents = addressComponents;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public OpeningHours getOpening_hours() {
        return opening_hours;
    }

    public void setOpening_hours(OpeningHours opening_hours) {
        this.opening_hours = opening_hours;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
