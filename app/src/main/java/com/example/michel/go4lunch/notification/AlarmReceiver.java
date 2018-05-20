package com.example.michel.go4lunch.notification;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


//create a broadcastReceiver for use notification
class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        // add intent for go  the NotificationService class
        Intent service_intent = new Intent(context, NotificationService.class);
        context.startService(service_intent);
    }
}
