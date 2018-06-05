package com.example.michel.go4lunch.APIMaps;


import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogMapService {



    // CREATE REQUEST FOR GET NEARBY PLACE
    @GET("maps/api/place/nearbysearch/json?location=46.066667,5.316667&radius=10000&type=restaurant")
    Observable<GoogleApiA> getGoogleApi(@Query("key")String key);

    // CREATE REQUEST FOR GET SPECIFICS VALUES FROM RESTAURANT
    @GET("maps/api/place/details/json?")
    Observable<GoogleAPIplaceId> getGoogleAPIplaceId(
            @Query("key")String key,
            @Query("placeid")String place_id);


    // USE RETROFIT FOR GET DATA
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}

