package com.example.michel.go4lunch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class AuthActivity extends AppCompatActivity {



    // DECLARE VALUE FOR FIRE BASE AUTH
    private static final int RC_SIGN_IN = 23;

    // CHOOSE AUTHENTIFICATION PROVIDERS
    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.GoogleBuilder().build(),
            new AuthUI.IdpConfig.FacebookBuilder().build());






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);


        // METHOD FOR LAUNCH SIGN IN INTENT
        this.launchSingInIntent();




    }


    // CREATE AND LAUNCH SIGN IN INTENT
    private void launchSingInIntent(){

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
    }


    // WHEN THE SIGN IN FLOW IS COMPLETE, WE RECEIVE THE RESULT IN ON ACTIVITY RESULT
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


                // START INTENT
                //startActivity(new Intent(AuthActivity.this,MainActivity.class));
                Toast.makeText(this,"La connection est établie", Toast.LENGTH_SHORT).show();


                // ...
            } else {
                // Sign in failed, check response for error code
                // ...
                Toast.makeText(this,"La connection n'a pu être établie", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
