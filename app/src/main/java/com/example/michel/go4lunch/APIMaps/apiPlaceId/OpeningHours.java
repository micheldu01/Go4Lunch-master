package com.example.michel.go4lunch.APIMaps.apiPlaceId;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OpeningHours {


    @SerializedName("periods")
    @Expose
    private List<Periods> periods;

    public OpeningHours(List<Periods> periods) {
        this.periods = periods;
    }

    public List<Periods> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Periods> periods) {
        this.periods = periods;
    }
}
