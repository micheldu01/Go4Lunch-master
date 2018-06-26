package com.example.michel.go4lunch.notification;



import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
                    String my_restaurant = user.getName_restaurant();

                    // ASK DATA BASE ADDRESS
                    DocumentReference docRef = db.collection("restaurant").document(my_choice);
                    docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {

                            // PUT DATA ON OBJECT RESTAURANT
                            ObjectRestaurant objectRestaurant = documentSnapshot.toObject(ObjectRestaurant.class);

                            // GET ADDRESS
                            String address = objectRestaurant.getAddress();


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
                                                    String str = (String) document.getData().get("name");

                                                    Log.e("--------", "--- name worker ---"+ str);

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

}







