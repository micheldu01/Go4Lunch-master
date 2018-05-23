package com.example.michel.go4lunch.notification;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmRestaurant extends BroadcastReceiver {


    // SET INTENT
    @Override
    public void onReceive(Context context, Intent intent) {

        // TEST
        Toast.makeText(context,"--- c'est mon alarm ---", Toast.LENGTH_LONG).show();
        Log.e("-- alarm --","-- c'est ok --");


        // add intent for go  the NotificationService class
        Intent service_intent = new Intent(context, NotificationRestaurant.class);
        context.startService(service_intent);
    }
}
