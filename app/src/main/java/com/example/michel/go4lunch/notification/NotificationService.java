package com.example.michel.go4lunch.notification;


import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;




public class NotificationService extends FirebaseMessagingService {



    // IMPLEMENT METHOD ON MESSAGE RECEIVED
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // NOTIFICATION EXIST
        if (remoteMessage.getNotification() != null){

            // 1 GET MESSAGE SENT BY FIREBASE
            String message = remoteMessage.getNotification().getBody();

            // 2 SHOW MESSAGE IN CONSOLE
            Log.e("TAG",message);
        }
    }
}
