package com.example.michel.go4lunch;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.michel.go4lunch.APIMaps.MapStreams;
import com.example.michel.go4lunch.APIMaps.apiPlaceId.GoogleAPIplaceId;
import com.example.michel.go4lunch.models.ObjectRestaurant;
import com.example.michel.go4lunch.models.User;
import com.example.michel.go4lunch.recyclerview.adapter.AdapterShowRestaurant;
import com.example.michel.go4lunch.recyclerview.ChoiceRestaurant;
import com.example.michel.go4lunch.models.RestaurantObject;
import com.example.michel.go4lunch.recyclerview.adapter.AdapterWorkmates;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.internal.DebouncingOnClickListener;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import static com.example.michel.go4lunch.shared.Shared.ID_RESTAURANT;
import static com.example.michel.go4lunch.shared.Shared.MYSHARED;

public class ActivityShowRestaurant extends AppCompatActivity {


    // DECLARE RECYCLER VIEW
    @BindView(R.id.show_restaurant_recycler_view)
    RecyclerView recyclerView;

    // DECLARE LIST RESTAURANT OBJECT
    private List<ChoiceRestaurant> choiceRestaurantList = new ArrayList<>();

    // DECLARE BUTTON CALL
    @BindView(R.id.image_button_phone_show_restaurant)
    ImageButton imageButtonCall;

    // DECLARE BUTTON LIKE
    @BindView(R.id.image_button_star_show_restaurant)
    ImageButton imageButtonLike;

    // DECLARE BUTTON WEBSITE
    @BindView(R.id.image_button_public_show_restaurant)
    ImageButton imageButtonWeb;

    // DECLARE BUTTON GREEN
    @BindView(R.id.button_green_show_restaurant)
    ImageView imageViewButtonGreen;

    // DECLARE BUTTON RED
    @BindView(R.id.button_red_show_restaurant)
    ImageView imageViewButtonRed;

    // DECLARE IMAGE VIEW PHOTO RESTAURANT
    @BindView(R.id.image_view_show_restaurant)
    ImageView imageView_restaurant;

    // DECLARE VIEW
    private View view;

    // DECLARE DATA BASE
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    // SHARED PREFERENCE
    private SharedPreferences preferences;

    // DECLARE TEXT NAME RESTAURANT
    @BindView(R.id.name_restaurant_show_restaurant)
    TextView name_restaurant;

    // DECLARE TEXT ADDRESS
    @BindView(R.id.address_restaurant_show_restaurant)
    TextView address;

    // NUMBER PHONE
    private String number_phone;

    // SITE INTERNET
    private String web_site;

    // PHOTO RESTAURANT
    private String photo_retaurant;

    // STRING ID RESTAURANT
    private String id_restaurant;

    // STRING CHOICE RESTAURANT
    private String choice_restaurant;

    // STRING URL FOR RATING
    private String url_rating_restaurant;

    // DOUBLE RATING RESTAURANT
    private double rating;

    // STAR RATING BAR
    @BindView(R.id.rating_bar)
    RatingBar ratingBar;

    // DECLARE NAME RESTAURANT FOR SAVE INTO USER
    private String name_restaurant_user;

    // DECLARE LIST RESTAURANT OBJECT
    private ArrayList<User> list_workmate = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_restaurant);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.parseColor("#808080"));
        }

        // DECLARE BUTTER KNIFE
        ButterKnife.bind(this);

        // COLOR GRAY
        this.colorGray();

        // SHOW WORkATES
        this.showProfile();

        // GET VALUES RESTAURANT
        this.getValuesRestaurant();

        // GET DATA USER
        this.getDataFromUsers();






    }

    // METHOD BACKGROUND LAYOUT PARAMS
    private void colorGray() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.parseColor("#808080"));
        }

    }

    // IMPLEMENT BUTTON

    // BUTTON PHONE
    @OnClick(R.id.image_button_phone_show_restaurant)
    void submitImageBuutonCall(View view) {

        // IF PHONE NUMBER EXIST
        if(number_phone!=null){

            // DECLARE INTENT ACTION DIAL
            Intent intent = new Intent(Intent.ACTION_DIAL);
            // IMPLEMENT INTENT WITH PHONE NUMBER
            intent.setData(Uri.parse("tel:"+number_phone));
            // START ACTIVITY
            startActivity(intent);

        }else{

            // TOAST IF CLICK
            Toast.makeText(this, "Phone not available.", Toast.LENGTH_LONG).show();

        }


    }

    // BUTTON LIKE
    @OnClick(R.id.image_button_star_show_restaurant)
    void submitImageBuutonLike(View view) {

        // DIRECTING FOR GOOGLE PAGE RESTAURANT FOR RATING

        // DECLARE AND IMPLEMENT INTENT WITH URL
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.fr/search?q="+url_rating_restaurant));
        // START INTENT
        startActivity(intent);


    }





    // BUTTON WEB SITE
    @OnClick(R.id.image_button_public_show_restaurant)
    void submitImageBuutonWeb(View view) {

        // IF WEB SITE EXIST
        if(web_site!=null){

            // DECLARE AND IMPLEMENT INTENT WITH URL
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(web_site));
            // START INTENT
            startActivity(intent);

        }else{

            // TOAST IF CLICK
            Toast.makeText(this, "Web site not available.", Toast.LENGTH_LONG).show();
        }

    }


    // BUTTON GREEN
    @OnClick(R.id.button_green_show_restaurant)
    void submitImageViewButtonGreen(View view) {

        // PUT ID RESTAURANT INTO USER FIRE BASE
        Map<String, Object> user = new HashMap<>();

        // CHOICE RESTAURANT
        user.put("choice", "");
        // NAME RESTAURANT
        user.put("name_restaurant","");

        // CREATE DOCUMENT USERS WITH ID PROFILE AUTH
        db.collection("users").document(FirebaseAuth.getInstance().getUid())
                .set(user, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.e("main activity", "------data save url restaurant-----");
                    }
                });


        // TOAST IF CLICK
        Toast.makeText(this, "-----DELETE CHOICE RESTAURANT----", Toast.LENGTH_SHORT).show();
        imageViewButtonGreen.setVisibility(view.INVISIBLE);
        imageViewButtonRed.setVisibility(view.VISIBLE);

    }

    // BUTTON RED
    @OnClick(R.id.button_red_show_restaurant)
    void submitImageViewButtonRed(View view) {

        // PUT ID AND NAME RESTAURANT INTO USER FIRE BASE
        Map<String, Object> user = new HashMap<>();

        // CHOICE RESTAURANT
        user.put("choice", id_restaurant);
        // NAME RESTAURANT
        user.put("name_restaurant",name_restaurant_user);



        // CREATE DOCUMENT USERS WITH ID PROFILE AUTH
        db.collection("users").document(FirebaseAuth.getInstance().getUid())
                .set(user, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });


        // TOAST IF CLICK
        Toast.makeText(this, "-----RESTAURANT CHOICE----", Toast.LENGTH_SHORT).show();
        imageViewButtonRed.setVisibility(view.INVISIBLE);
        imageViewButtonGreen.setVisibility(view.VISIBLE);
    }


    // METHOD FOR GET ADDRESS
    private void getValuesRestaurant() {

        // IMPLEMENT SHARED PREFERENCES
        preferences = getSharedPreferences(MYSHARED, Context.MODE_PRIVATE);

        // PUT ID RESTAURANT FROM SHARED INTO STRING id_restaurant
        id_restaurant = preferences.getString(ID_RESTAURANT, "");


        // GET DATA RESTAURANT FROM FIRE BASE WITH OBJECT RESTAURANT
        DocumentReference docRef = db.collection("restaurant").document(id_restaurant);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                ObjectRestaurant objectRestaurant = documentSnapshot.toObject(ObjectRestaurant.class);


                // IMPLEMENT TEXT VIEW NAME RESTAURANT
                name_restaurant.setText(objectRestaurant.getNameRestaurant());
                Log.e("---TAG---", "-- name restaurant --" + objectRestaurant.getNameRestaurant());

                // IMPLEMENT NAME RESTAURANT FOR USER
                name_restaurant_user = objectRestaurant.getNameRestaurant();

                // IMPLEMENT TEXT VIEW ADDRESS
                address.setText(objectRestaurant.getAddress());
                Log.e("---TAG---", "-- address --" + objectRestaurant.getAddress());

                // GET DATA WITH GOOGLE API STREAM
                getDataFromApi(objectRestaurant.getPlace_id());
                Log.e("---TAG---", "-- place id --" + objectRestaurant.getPlace_id());

                // IMPLEMENT URL WITH NAME RESTAURANT
                url_rating_restaurant = "https://www.google.fr/search?q="+objectRestaurant.getNameRestaurant();
                Log.e("---TAG---", "--url rating restaurant --" + objectRestaurant.getPlace_id());


                // IMPLEMENT RATING RESTAURANT
                rating = objectRestaurant.getRating();
                Log.e("---TAG---", "--rating --" + objectRestaurant.getRating());

                // SHOW RATING BAR
                ratingBar.setRating((float) rating);




            }
        });

    }

    // METHOD WITH STREAM FOR GET DATA FROM API GOOGLE MAP
    private void getDataFromApi(String id_place) {


        // DECLARE DISPOSABLE WITH STREAM GOOGLE API PLACE ID
        Disposable disposable = MapStreams.streamGoogleAPIplaceId(BuildConfig.KEY_GOOGLE_MAP, id_place)
                .subscribeWith(new DisposableObserver<GoogleAPIplaceId>() {
                    @Override
                    public void onNext(GoogleAPIplaceId googleAPIplaceId) {

                            // IMPLEMENT NUMBER PHONE
                            number_phone = googleAPIplaceId.getResultsAPI().getPhone();
                            Log.e("--get phone--", "--result--" + number_phone);

                            // IMPLEMENT WEB SITE
                            web_site = googleAPIplaceId.getResultsAPI().getWebsite();
                            Log.e("--get web site--", "--result--" + web_site);


                        // GET PHOTO RESTAURANT
                        // IF PHOTO IS NOT NULL GET PHOTO
                        if(googleAPIplaceId.getResultsAPI().getPhotos()!=null){
                            photo_retaurant = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=800&photoreference="
                                    + googleAPIplaceId.getResultsAPI().getPhotos().get(0).getPhotoReference()+"&key=" + BuildConfig.KEY_GOOGLE_MAP;
                            Log.e("--photo restaurant--", "--result--" + photo_retaurant);
                        }


                        // IMPLEMENT PHOTO RESTAURANT IF PHOTO EXIST
                        if(photo_retaurant!=null){
                            Glide.with(ActivityShowRestaurant.this).load(photo_retaurant).into(imageView_restaurant);
                        }


                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

    // GET USER DATA FROM DATA BASE
    private void getDataFromUsers(){

        // GET DATA RESTAURANT FROM FIRE BASE WITH OBJECT RESTAURANT
        DocumentReference docRef = db.collection("users").document(FirebaseAuth.getInstance().getUid());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                // DECLARE AND IMPLEMENT USER
                User user = documentSnapshot.toObject(User.class);

                // IMPLEMENT CHOICE RESTAURANT
                choice_restaurant = user.getChoice();

                // IMPLEMENT METHOD COMPARE
                choiceEqualThisRestaurant(choice_restaurant);
            }
        });

    }

    // IF CHOICE EQUAL THIS RESTAURANT
    private void choiceEqualThisRestaurant(String choice_restaurant){

        // IF CHOICE EQUAL ID RESTAURANT SHOW BUTTON GREEN
        if(choice_restaurant.equals(id_restaurant)){

            // HIDE RED AND SHOW GREEN
            imageViewButtonGreen.setVisibility(view.VISIBLE);
            imageViewButtonRed.setVisibility(view.INVISIBLE);

        }else{

            // HIDE RED AND SHOW GREEN
            imageViewButtonGreen.setVisibility(view.INVISIBLE);
            imageViewButtonRed.setVisibility(view.VISIBLE);
        }
    }

    // METHOD SHOW PROFILE CHOICE
    private void showProfile(){

        // GET OBJECT USERS FROM CLOUD FIRESTORE

        // GET USERS COLLECTION FROM CLOUD
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        // IF TASK IS SUCCESS FUL GET DATA
                        if (task.isSuccessful()){

                            // GET DATA FROM DATA BASE
                            for (QueryDocumentSnapshot document : task.getResult()){

                                // ASK IF CHOICE IS DIFFERENT OF NULL
                                if (!document.getData().equals(null)) {

                                    // SPLIT DATA "="
                                    String currentString = document.getData().toString();
                                    String[] separated = currentString.split("=");

                                    // UID
                                    String uid = separated[1];
                                    String[] separated_uid = uid.split(",");
                                    String uid_final = separated_uid[0];

                                    // USERNAME
                                    String username = separated[2];
                                    String[] separated_username = username.split(",");
                                    String username_final = separated_username[0];

                                    // URL PHOTO
                                    String urlPhoto = separated[4];
                                    String[] separated_url = urlPhoto.split(",");
                                    String urlPhoto_final = separated_url[0];

                                    // CHOICE WORKMATES
                                    String choice = separated[5];
                                    choice = choice.replace("}","");

                                    // SELECT WORKMATES
                                    // IF UID = CURRENT USER DON'T SHOW
                                    if (!FirebaseAuth.getInstance().getUid().equals(uid_final)){

                                        // ADD IF WORKMATE CHOICE THIS RESTAURANT
                                        if (choice.equals(id_restaurant)){

                                            // IMPLEMENT OBJECT USER LIST
                                            list_workmate.add(new User(username_final,urlPhoto_final));
                                        }
                                    }

                                    // IMPLEMENT RECYCLER VIEW
                                    recyclerView.setLayoutManager(new LinearLayoutManager(ActivityShowRestaurant.this));
                                    recyclerView.setAdapter(new AdapterShowRestaurant(list_workmate));

                                }
                            }
                        }
                    }
                });
    }
}



















































