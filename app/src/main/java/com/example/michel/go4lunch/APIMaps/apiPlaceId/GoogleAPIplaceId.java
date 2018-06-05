package com.example.michel.go4lunch.APIMaps.apiPlaceId;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GoogleAPIplaceId {



    // DECLARE VAUE
    @SerializedName("result")
    @Expose
    public ResultAPI resultsAPI;



    public GoogleAPIplaceId(ResultAPI resultsAPI) {
        this.resultsAPI = resultsAPI;
    }

    public ResultAPI getResultsAPI() {
        return resultsAPI;
    }

    public void setResultsAPI(ResultAPI resultsAPI) {
        this.resultsAPI = resultsAPI;
    }

}
