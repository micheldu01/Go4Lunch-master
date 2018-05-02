package com.example.michel.go4lunch.base;


import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;

public abstract class BaseActivity extends AppCompatActivity {


    // ERROR HANDLER METHOD
    protected OnFailureListener onFailureListener(){
        return new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"ERROR", Toast.LENGTH_SHORT).show();
            }
        };
    }

}
