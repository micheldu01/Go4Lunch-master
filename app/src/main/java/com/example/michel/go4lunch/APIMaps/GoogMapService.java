package com.example.michel.go4lunch.APIMaps;


import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface GoogMapService {


    // CREATE REQUEST FOR TOP STORIES API NYT
    @GET("maps/api/place/nearbysearch/json?location=46.066667,5.316667&radius=10000&type=restaurant&key=AIzaSyBTdkUz22ebr4rMq63NRDdzCFcB9XX8Uqs")
    Observable<GoogleAPI> getGoogleApi();


    // USE RETROFIT FOR GET DATA
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}

