package com.example.michel.go4lunch.APIMaps.apiPlaceId;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OpeningHours {


    // DECLARE LIST PERIODS
    @SerializedName("periods")
    @Expose
    private List<Periods> periods;

    // DECLARE BOOLEAN OPEN NOW
    @SerializedName("open_now")
    @Expose
    private boolean open_now;



    // CONSTRUCTOR WITH LIST PERIODS
    public OpeningHours(List<Periods> periods) {
        this.periods = periods;
    }


   // CONSTRUCTOR WITH BOOLEAN OPEN NOW
    public OpeningHours(boolean open_now) { this.open_now = open_now; }



    // GETTER AND SETTER

    public List<Periods> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Periods> periods) {
        this.periods = periods;
    }

    public boolean isOpen_now() { return open_now; }

    public void setOpen_now(boolean open_now) { this.open_now = open_now; }
}
