package com.example.michel.go4lunch;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.michel.go4lunch.adapter.PageAdapter;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // DECLARE VALUE FOR FIRE BASE AUTH
    private static final int RC_SIGN_IN = 23;

    // DECLARE COLLBACK MANAGER
    private CallbackManager callbackManager;

    // BUTTON
    private LoginButton button_facebook;

    // CHOOSE AUTHENTIFICATION PROVIDERS
    List<AuthUI.IdpConfig> providers = Arrays.asList(
            //new AuthUI.IdpConfig.GoogleBuilder().build(),
            new AuthUI.IdpConfig.FacebookBuilder().build());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // test
        callbackManager = CallbackManager.Factory.create();




        button_facebook = (LoginButton) findViewById(R.id.login_button_facebook);
        button_facebook.setReadPermissions("email");
        // If using in a fragment
        //button_facebook.setFragment(this);

        // Callback registration
        button_facebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Toast.makeText( MainActivity.this,"connect OK", Toast.LENGTH_SHORT).show();
                // START INTENT
                startActivity(new Intent(MainActivity.this, ScreenActivity.class));
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });


        // METHOD FOR LAUNCH SIGN IN INTENT
        //this.launchSingInIntent();





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
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

/*
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

                // INTENT
                startActivity(new Intent(MainActivity.this,ScreenActivity.class));

                // ...
            } else {
                // Sign in failed, check response for error code
                // ...
                Toast.makeText(this,"La connection n'a pu être établie", Toast.LENGTH_SHORT).show();
            }
        }
    }*/
}






































