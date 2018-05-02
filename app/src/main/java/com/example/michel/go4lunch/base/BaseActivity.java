package com.example.michel.go4lunch.base;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public abstract class BaseActivity extends AppCompatActivity {




    // CURRENT USER
    @Nullable
    protected FirebaseUser getCurrentUser(){
        return FirebaseAuth.getInstance().getCurrentUser();
    }


    // ERROR HANDLER METHOD
    protected OnFailureListener ifOnFailureListener(){
        return new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"ERROR", Toast.LENGTH_SHORT).show();
            }
        };
    }

}
