package com.example.michel.go4lunch.api_google_maps;




import android.location.Location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class Results {


    // DECLARE VALUES
    @SerializedName("geometry")
    @Expose
    public String geometry;

    @SerializedName("location")
    @Expose
    public Location location;

    @SerializedName("id")
    @Expose
    String id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("photos")
    @Expose
    public List<PhotoGoogle> photos;


    // CONSTRUCTOR EMPTY
    public Results() {}


    // CONSTRUCTOR WITHOUT PHOTO
    public Results(String geometry, Location location, String id, String name) {
        this.geometry = geometry;
        this.location = location;
        this.id = id;
        this.name = name;
    }


    // CONSTRUCTOR WITH PHOTO

    public Results(String geometry, Location location, String id, String name, List<PhotoGoogle> photos) {
        this.geometry = geometry;
        this.location = location;
        this.id = id;
        this.name = name;
        this.photos = photos;
    }


    // GETTER
    public String getGeometry() {
        return geometry;
    }

    public Location getLocation() {return location;}

    public String getId() { return id; }

    public String getName() {
        return name;
    }

    public List<PhotoGoogle> getPhotos() {
        return photos;
    }
}

























