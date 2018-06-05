package com.example.michel.go4lunch.APIMaps.apiPlaceId;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AddressComponent {



    // DECLARE VALUES
    @SerializedName("short_name")
    @Expose
    private String short_name;


    // CONSTRUCTOR
    public AddressComponent(String short_name) {
        this.short_name = short_name;
    }


    // GETTER AND SETTER

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }
}



