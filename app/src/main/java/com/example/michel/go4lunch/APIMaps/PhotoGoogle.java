package com.example.michel.go4lunch.APIMaps;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PhotoGoogle {


    // DECLARE VALUE
    @SerializedName("photo_reference")
    @Expose
    private String photoReference;


    // CONSTRUCTOR EMPTY
    public PhotoGoogle() {}

    // CONSTRUCTOR WITH VALUE
    public PhotoGoogle(String photoReference) {
        this.photoReference = photoReference;
    }

    // GETTER
    public String getPhotoReference() {
        return photoReference;
    }
}
