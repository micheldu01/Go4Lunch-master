package com.example.michel.go4lunch.APIMaps;


import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MapStreams {


    // CREATE OBSERVABLE FOR GET STREAM TOP STORIES NYT
    public static Observable<GoogleAPI> streamGoogleApi() {
        GoogMapService googMapService = GoogMapService.retrofit.create(GoogMapService.class);
        return googMapService.getGoogleApi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
}
