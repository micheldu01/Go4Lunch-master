package com.example.michel.go4lunch.APIMaps.apiPlaceId;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OpeningHours {


    // DECLARE VALUE
    @SerializedName("periods")
    @Expose
    private List<Periods> periods;


    // CONSTRUCTOR
    public OpeningHours(List<Periods> periods) {
        this.periods = periods;
    }


    // GETTER AND SETTER

    public List<Periods> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Periods> periods) {
        this.periods = periods;
    }
}
