package com.example.michel.go4lunch.APIMaps.apiNearby;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geometry {

    // DECLARE LOCATION
    @SerializedName("location")
    @Expose
    public Location location;

    // CREATE CONSTRUCTOR
    public Geometry(Location location) {
        this.location = location;
    }


    // DECLARE GETTER AND SETTER
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
