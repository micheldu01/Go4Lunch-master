package com.example.michel.go4lunch.APIMaps.apiNearby;




import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Results {


    // DECLARE VALUES
    @SerializedName("geometry")
    @Expose
    private Geometry geometry;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("photos")
    @Expose
    private List<PhotoGoogle> photos;

    @SerializedName("vicinity")
    @Expose
    private String vicinity;

    @SerializedName("opening_hours")
    @Expose
    @Nullable private Opening_hours opening_hours;

    @SerializedName("place_id")
    @Expose
    @Nullable private String place_id;

    @SerializedName("rating")
    @Expose
    @Nullable private double rating;






    // CONSTRUCTOR EMPTY
    public Results(String name) {
        this.name = name;
    }


    // CONSTRUCTOR WITH PHOTO

    public Results(Geometry geometry, String id, String name, List<PhotoGoogle> photos, String vicinity, Opening_hours opening_hours, String place_id, double rating) {
        this.geometry = geometry;
        this.id = id;
        this.name = name;
        this.photos = photos;
        this.vicinity = vicinity;
        this.opening_hours = opening_hours;
        this.place_id = place_id;
        this.rating = rating;
    }


    //  GETTER AND SETTER

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PhotoGoogle> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoGoogle> photos) {
        this.photos = photos;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    @Nullable
    public Opening_hours getOpening_hours() {
        return opening_hours;
    }

    public void setOpening_hours(@Nullable Opening_hours opening_hours) {
        this.opening_hours = opening_hours;
    }

    @Nullable
    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(@Nullable String place_id) {
        this.place_id = place_id;
    }

    @Nullable
    public double getRating() {
        return rating;
    }

    public void setRating(@Nullable double rating) {
        this.rating = rating;
    }
}

























