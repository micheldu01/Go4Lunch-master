package com.example.michel.go4lunch.APIMaps.apiPlaceId;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Periods {


    // DECLARE VALUE
    @SerializedName("close")
    @Expose
    public Close close;

    // CONSTRUCTOR
    public Periods(Close close) {
        this.close = close;
    }


    // GETTER AND SETTER
    public Close getClose() {
        return close;
    }

    public void setClose(Close close) {
        this.close = close;
    }
}
