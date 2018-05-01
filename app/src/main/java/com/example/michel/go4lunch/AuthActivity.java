package com.example.michel.go4lunch;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;
import java.util.List;

public class AuthActivity extends AppCompatActivity {


    // F A C E B O O K --------------------
    // DECLARE VALUE FOR FIRE BASE AUTH
    private static final int RC_SIGN_IN = 23;

    // CALLBACK FACEBOOK
    private CallbackManager callbackManager;

    // LOGIN BUTTON FACEBOOK
    private LoginButton loginButtonFacebook;
    //-----------------------------------------



    // G O O G L E ---------------------------------
    // CONSTANT FOR DETECTING THE LOGIN INTENT RESULT
    private static final int RC_SIGN_I = 234;

    // TAG FOR LOGS OPTIONAL
    private static final String TAG = "simplifiedcoding";

    // CREATION A GOOGLE SINGLE IN CLIENT OBJECT
    GoogleSignInClient mGoogleSignInClient;

    // AND ALSO A FIRE BASE AUTH OBJECT
    FirebaseAuth mAuth;
    //----------------------------------------------------




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);


        //----------------------------------------------------
        //           F A C E B O O K
        // FACEBOOK
        callbackManager = CallbackManager.Factory.create();

        // FACEBOOK
        loginButtonFacebook = (LoginButton) findViewById(R.id.login_button_facebook);
        loginButtonFacebook.setReadPermissions("email");

        // Callback registration FACEBOOK
        loginButtonFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
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

        // FACEBOOK
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
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
        //----------------------------------------------------------------





        //---------------------------------------------------------------------
        //       G O O G L E
        //
        // FIRST WE INITIALIZED THE FIRE BASE AUTH OBJECT
        mAuth = FirebaseAuth.getInstance();

        // THEN WE NEED A GOOGLE SIGN IN OPTIONS OBJECT
        // AND WE NEED TO BUILD IT AS BELOW
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // THEN WE WILL GET THE GOOGLE SIGN IN CLIENT OBJECT FROM GOOGLE SIGN IN CLASS
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // NOW WE WILL ATTACH A CLICK LISTENER TO THE SIGN IN BUTTON
        // AND INSIDE ONCLICK() METHOD WE ARE CALLING THE SIGN IN() METHOD THAT WILL OPEN
        // GOOGLE SIGN IN INTENT
        findViewById(R.id.sign_in_button_google).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        //-------------------------------------------------------------------------

    }


    // FACEBOOK
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        // FACEBOOK
        // IF THE REQUEST CODE IS THE GOOGLE SIGN IN THAT WE DEFINED AT STARTING
        if (requestCode == RC_SIGN_I){
            // GETTING THE GOOGLE SIGN IN TASK
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                // GOOGLE SIGN IN WAS SUCCESSFUL AUTHENTICATE WITH FIRE BASE
                GoogleSignInAccount account = task.getResult(ApiException.class);

                // AUTHENTICATING WITH FIRE BASE
                firebaseAuthWithGoogle(account);
            }catch (ApiException e){
                Toast.makeText(AuthActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct){
        Log.e(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        // GETTING THE AUTH CREDENTIAL
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

        // NOW USING FIRE BASE WE ARE SIGNING IN THE USER HERE
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.e(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Toast.makeText(AuthActivity.this, "User Signed In", Toast.LENGTH_SHORT).show();
                        }else {
                            // IF SIGN IN FAILS, DISPLAY A MESSAGE TO THE USER
                            Log.e(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(AuthActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // THIS METHOD IS CALLED ON CLICK
    private void signIn(){

        // GETTING THE GOOGLE SIGN IN INTENT
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();

        // STARTING THE ACTIVITY FOR RESULT
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

}
