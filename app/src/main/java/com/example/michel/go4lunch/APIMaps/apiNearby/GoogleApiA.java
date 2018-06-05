package com.example.michel.go4lunch.APIMaps.apiNearby;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class GoogleApiA {


    // DECLARE VAUE
    @SerializedName("results")
    @Expose
    public List<Results> results = null;


    // CREATE CONSTRUCTOR WITHOUT VALUE
    public GoogleApiA() {}


    // CREATE CONSTRUCTOR
    public GoogleApiA(List<Results> results) {
        this.results = results;
    }


    // CREATE GETTER

    public List<Results> getResults() {
        return results;
    }
}
