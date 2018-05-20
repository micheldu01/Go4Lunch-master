package com.example.michel.go4lunch.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.michel.go4lunch.ActivityShowRestaurant;

class NotificationService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    // CREATE METHOD NOTIFICATION (TEXT, ICON ...)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        NotificationManager notify_manager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        // create intent
        Intent intent_main_activity = new Intent(this.getApplicationContext(), ActivityShowRestaurant.class);
        // create pendingIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent_main_activity, 0);

        Log.e("-- notification --", "---- is ok ----");

        // create notification poupup
        Notification notification_poupup = new Notification.Builder(this)
                //add title
                .setContentTitle("RESTAUANT")
                //add text
                .setContentText("Address of restaurant'\n'"+"List of workmates")
                //add icon
                //.setSmallIcon(R.mipmap.ic_launcher)
                //use pendingIntent
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        // use notify
        notify_manager.notify(0, notification_poupup);


        return START_NOT_STICKY;
    }


}
