package com.example.michel.go4lunch.notification;



import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.example.michel.go4lunch.ActivityShowRestaurant;
import com.example.michel.go4lunch.R;
import com.example.michel.go4lunch.models.ObjectRestaurant;
import com.example.michel.go4lunch.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class NotificationRestaurant extends Service {


    // DECLARE DATA BASE FIREBASE FIRESTORE
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    // DECLARE CHANNEL ID
    private static final String CHANNEL_ID = "channel_id";

    // NAME RESTAURANT
    private String my_restaurant;

    // ADDRESS RESTAURANT
    private String address;

    // NAME WORKMATES
    private String name_workmates;



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {return null;}




    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        // CHANNEL ID FOR API 26 +
        createNotificationChannel();

        // GET DATA FROM DATABASE
        getWokmatesRestaurant();





        return START_NOT_STICKY;

    }



    // METHOD FOR GET WORKMATES AND RESTAURANT FOR NOTIFICATION
    private void getWokmatesRestaurant() {


        // GET MY CURRENT UID
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {

            // ASK DATABASE
            DocumentReference docRef = db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
            docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {

                   // GET USER CLASS TO PUT DATA INTO
                    User user = documentSnapshot.toObject(User.class);

                    // GET MY CHOICE
                    final String my_choice = user.getChoice();

                    // GET NAME RESTAURANT
                    my_restaurant = user.getName_restaurant();

                    // ASK DATA BASE ADDRESS
                    DocumentReference docRef = db.collection("restaurant").document(my_choice);
                    docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {

                            // PUT DATA ON OBJECT RESTAURANT
                            ObjectRestaurant objectRestaurant = documentSnapshot.toObject(ObjectRestaurant.class);

                            // GET ADDRESS
                            address = objectRestaurant.getAddress();

                            // ASK DATA BASE FOR GET NAME WORKMATES
                            db.collection("users")
                                    .whereEqualTo("choice", my_choice)
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (task.isSuccessful()) {

                                                // GET DATA
                                                for (QueryDocumentSnapshot document : task.getResult()) {

                                                    // GET NAME WORKMATES SAME CHOICE OF ME
                                                    name_workmates = (String) document.getData().get("name");

                                                    Log.e("--------", "--- name worker ---"+ name_workmates);


                                                    // DECLARE AND IMPLEMENT NOTIFICATION COMPAT
                                                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(NotificationRestaurant.this, CHANNEL_ID)

                                                            // ADD ICON
                                                            .setSmallIcon(R.drawable.icon_restaurant_vert2)
                                                            // TITLE
                                                            .setContentTitle(my_restaurant)
                                                            // TEXT
                                                            .setContentText(my_restaurant)
                                                            // ADD TEXT
                                                            .setStyle(new NotificationCompat.BigTextStyle()
                                                                    .bigText(address+"\n"+name_workmates))
                                                            //PRIORITY
                                                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                                                    
                                                    // DECLARE AND IMPLEMENT NOTIFICATION MANAGER COMPAT
                                                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(NotificationRestaurant.this);

                                                    // BUILD NOTIFICATION MANAGER WITH NOTIFICATION COMPAT
                                                    notificationManager.notify(100, mBuilder.build());

                                                }
                                            } else {
                                                Log.d("--else--", "Error getting documents: ", task.getException());
                                            }

                                        }
                                    });
                        }
                    });
                }
            });
        }
    }

    // METHOD FOR GET CHANNEL ID FOR NOTIFICATION FOR API 26 +
    private void createNotificationChannel() {

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = CHANNEL_ID;
            String description = "channel_id";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}







