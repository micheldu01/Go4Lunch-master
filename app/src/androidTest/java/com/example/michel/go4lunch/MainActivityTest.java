package com.example.michel.go4lunch;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MainActivityTest {


    // TEST DATABASE AUTH
    @Test
    public void name() {

        // DECLARE DATA BASE
        FirebaseFirestore db= FirebaseFirestore.getInstance();

        // TURN URI TO STRING
        String username = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();


        assertEquals("Michel Laurent",username);

    }

    // WRITE DATA INTO DATABASE


    @Test
    public void writeReadDatabase() {

        // WRITE INTO DATABASE

        // DECLARE DATA BASE
        FirebaseFirestore db= FirebaseFirestore.getInstance();

        // CREATE COLLECTION AND DOCUMENT
        CollectionReference mon_test = db.collection("ma_collection");

        // DECLARE HAS MAP
        Map<String,Object> data = new HashMap<>();

        // IMPLEMENT HAS MAP
        data.put("username","michel");
        data.put("name","laurent");

        // PUT DATA INTO DOCUMENT
        mon_test.document("mon_document").set(data);


        // READ DATABASE

        //
        DocumentReference docRef = db.collection("ma_collection").document("mon_document");

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){

                        assertArrayEquals(new String[]{"name=laurent", "username=michel"},new String[] {String.valueOf(document.getData())});

                    }else {
                    }
                }else {
                }
            }
        });


    }
}