package com.example.michel.go4lunch.APIMaps.apiNearby;


import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Opening_hours {


    // DECLARE BOOLEAN OPEN NOW
    @SerializedName("open_now")
    @Expose
    @Nullable public boolean open_now;



    // CONSTRUCTOR
    public Opening_hours(boolean open_now) {
        this.open_now = open_now;
    }


    // GUETTER AND SETTER

    @Nullable
    public boolean isOpen_now() {
        return open_now;
    }

    public void setOpen_now(@Nullable boolean open_now) {
        this.open_now = open_now;
    }
}

