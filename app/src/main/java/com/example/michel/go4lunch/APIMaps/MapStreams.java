package com.example.michel.go4lunch.APIMaps;


import com.example.michel.go4lunch.APIMaps.apiNearby.GoogleApiA;
import com.example.michel.go4lunch.APIMaps.apiPlaceId.GoogleAPIplaceId;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MapStreams {


    // CREATE OBSERVABLE FOR GET STREAM TOP STORIES NYT
    public static Observable<GoogleApiA> streamGoogleApi(String key) {
        GoogMapService googMapService = GoogMapService.retrofit.create(GoogMapService.class);
        return googMapService.getGoogleApi(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    // CREATE OBSERVABLE FOR GET STREAM TOP STORIES NYT
    public static Observable<GoogleAPIplaceId> streamGoogleAPIplaceId(String key, String place_id) {
        GoogMapService googMapService = GoogMapService.retrofit.create(GoogMapService.class);
        return googMapService.getGoogleAPIplaceId(key,place_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

}
