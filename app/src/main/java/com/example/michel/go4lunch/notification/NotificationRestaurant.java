package com.example.michel.go4lunch.notification;



import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.michel.go4lunch.ActivityShowRestaurant;
import com.example.michel.go4lunch.R;



public class NotificationRestaurant extends Service {





    @Nullable
    @Override
    public IBinder onBind(Intent intent) {return null;}

/*
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        // using NotificationManager for get Alarm
        NotificationManager notify_manager = (NotificationManager)
               getSystemService(Context.NOTIFICATION_SERVICE);
        // create intent
        Intent intent_main_activity = new Intent(this.getApplicationContext(), ActivityShowRestaurant.class);
        // create pendingIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent_main_activity, 0);

        Log.e("----notificatio--------","-----ok---------");

        // create notification poupup
        Notification notification_poupup = new Notification.Builder(this)
                //add title
                .setContentTitle("NYT")
                //add text
                .setContentText("Il y a de nouveux articles")
                //add icon
                .setSmallIcon(R.mipmap.ic_launcher_round)
                //use pendingIntent
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        // use notify
        notify_manager.notify(1, notification_poupup);

            return START_NOT_STICKY;


    }

*/


}







