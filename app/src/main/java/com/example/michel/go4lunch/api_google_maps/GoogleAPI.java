package com.example.michel.go4lunch.api_google_maps;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class GoogleAPI {


    // DECLARE VAUE
    @SerializedName("results")
    @Expose
    public List<Results> results = null;


    // CREATE CONSTRUCTOR WITHOUT VALUE
    public GoogleAPI() {}


    // CREATE CONSTRUCTOR
    public GoogleAPI(List<Results> results) {
        this.results = results;
    }


    // CREATE GETTER

    public List<Results> getResults() {
        return results;
    }
}
