package com.example.michel.go4lunch.auth;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.michel.go4lunch.MainActivity;
import com.example.michel.go4lunch.R;
import com.example.michel.go4lunch.api.UserHelper;
import com.example.michel.go4lunch.base.BaseActivity;
import com.example.michel.go4lunch.models.User;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class AuthActivity extends BaseActivity {


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
        //
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        // IMPLEMENT MENU WHIT LOGIN

                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        Log.v("AuthActivity", response.toString());
                                        Log.e("AuthActivity","reponse FACEBOOK = " + response.toString());

                                        // GET USER PROFILE
                                        try {
                                            String id = object.getString("id");
                                            String name = object.getString("name");
                                            String email = object.getString("email");
                                            Log.e("AuthActivity","reponse FACEBOOK = "+ name + email);

                                            // CALL METHOD FOR GET DATA FROM PROFILE FACEBOOK INTO DATABASE
                                            createUsersDatabase(id,name,email,"https://graph.facebook.com/"+id+"/picture?type=large");

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,gender,birthday");
                        request.setParameters(parameters);
                        request.executeAsync();



                        // START INTENT
                        startActivity(new Intent(AuthActivity.this,MainActivity.class));


                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Log.v("LoginActivity", exception.getCause().toString());

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
        if (requestCode == RC_SIGN_IN){
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

    //-------------------------------------------------------------------------------------------------------------------
    //                   G  O  O  G  L  E
    //
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
                            // IF GOOGLE IS CONNECT
                            Log.e(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            // GET DATE FROM GOOGLE
                            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(AuthActivity.this);
                            if (acct != null) {
                                String personName = acct.getDisplayName();
                                String personGivenName = acct.getGivenName();
                                String personFamilyName = acct.getFamilyName();
                                String personEmail = acct.getEmail();
                                String personId = acct.getId();
                                Uri personPhoto = acct.getPhotoUrl();

                                // CHANGE URI TO STRING
                                Uri uri = personPhoto;
                                String stringUri;
                                stringUri = uri.toString();

                                // CALL METHOD FOR PUT PROFILE GOOGLE INTO DATABASE
                                createUsersDatabase(personId,personName,personEmail,stringUri);

                                // MAKE TOAST
                                Toast.makeText(AuthActivity.this, "User Signed In", Toast.LENGTH_SHORT).show();
                                // START INTENT
                                startActivity(new Intent(AuthActivity.this, MainActivity.class));
                            }






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




    // METHOD FOR GET PROFILE FACEBOOK
    private void createUsersDatabase(String uid, String username, String email, String urlPicture){

        // IMPLEMENT FIREBASE STORE
        FirebaseFirestore db = FirebaseFirestore.getInstance();


            // GET USER PROFILE
            User user = new User(uid, username, email, urlPicture);
            db.collection("users").document("user1").set(user)

                    // SEND MESSAGE IN LOGCAT IF FAILED
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("USERS","FAILED");

                        }
                    });
    }

}
