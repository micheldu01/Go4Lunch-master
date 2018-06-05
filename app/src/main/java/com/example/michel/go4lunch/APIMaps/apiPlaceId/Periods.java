package com.example.michel.go4lunch.APIMaps.apiPlaceId;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Periods {


    @SerializedName("close")
    @Expose
    public Close close;

    public Periods(Close close) {
        this.close = close;
    }

    public Close getClose() {
        return close;
    }

    public void setClose(Close close) {
        this.close = close;
    }
}
