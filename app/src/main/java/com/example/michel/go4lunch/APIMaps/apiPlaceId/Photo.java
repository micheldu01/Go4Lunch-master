package com.example.michel.go4lunch.APIMaps.apiPlaceId;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {


    // DECLARE VALUE
    @SerializedName("photo_reference")
    @Expose
    public String photoReference;


    // CONSTRUCTOR
    public Photo(String photoReference) {
        this.photoReference = photoReference;
    }


    // GETTER AND SETTER

    public String getPhotoReference() {
        return photoReference;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }
}
