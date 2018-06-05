package com.example.michel.go4lunch.APIMaps.apiNearby;


import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    // DECLARE DOUBLE LATITUDE
    @SerializedName("lat")
    @Expose
    @Nullable private Float latitude;

    // DECLARE DOUBLE LATITUDE
    @SerializedName("lng")
    @Expose
    @Nullable private Float longitude;


    public Location(Float latitude, Float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }
}






































