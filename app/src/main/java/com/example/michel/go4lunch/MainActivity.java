package com.example.michel.go4lunch;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.michel.go4lunch.adapter.PageAdapter;
import com.example.michel.go4lunch.models.ObjectRestaurant;
import com.example.michel.go4lunch.models.User;
import com.example.michel.go4lunch.notification.ActivityNoticationShow;
import com.example.michel.go4lunch.recyclerview.adapter.AdapterWorkmates;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.michel.go4lunch.shared.Shared.ID_RESTAURANT;
import static com.example.michel.go4lunch.shared.Shared.MYSHARED;


public class MainActivity extends AppCompatActivity
        implements
        NavigationView.OnNavigationItemSelectedListener{


    // DECLARE FOR SIGN IN
    private static final int RC_SIGN_IN = 124;

    // CHOOSE AUTHENTIFICATION PROVIDERS FACEBOOK AND GOOGLE
    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.EmailBuilder().build(),
            new AuthUI.IdpConfig.FacebookBuilder().build(),
            new AuthUI.IdpConfig.GoogleBuilder().build());



    // DECLARE TOOLBAR
    @BindView(R.id.activity_main_toolbar) Toolbar toolbar;

    // DECLARE DRAWER LAYOUT
    @BindView(R.id.activity_main_drawer_layout) DrawerLayout drawerLayout;

    // DECLARE NAVIGATION VIEW
    @BindView(R.id.activity_main_nav_view) NavigationView navigationView;

    // VIEW PAGER
    @BindView(R.id.activity_main_viewpager) ViewPager pager;

    // DECLARE TAB LAYOUT
    @BindView(R.id.activity_main_tabs) TabLayout tabs;

    // DECLARE DATA BASE FIREBASE FIRESTORE
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    // ADD ARRAY ICONS
    private int[] tabIcons = {R.drawable.ic_map_black_24dp,R.drawable.ic_view_list_black_24dp,R.drawable.ic_group_black_24dp};

    // MENU DRAWER
    private NavigationView navView;

    // FIRE BASE
    private FirebaseAuth mAuth;

    // FIRE BASE USER
    private FirebaseUser firebaseUser;

    // AUTOCOMPLETE
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DECLARE BUTTER KNIFE
        ButterKnife.bind(this);


        // METHOD FOR AUTH
        this.launchSignInIntent();

        // 1.toolbar add toolbar method
        this.configureToolbar();

        //TabLayout execute tabLayout
        this.configureViewPagerAndTabs();

        //NavigationView call method
        this.configureNavigationView();

        //DrawerLayout call method
        this.configureDrawerLayout();

        // DRAWER MENU PROFILE
        this.profileIntoDrawerMenu();

        // PUSH DATA PROFILE INTO FIREBASE
        this.saveProfileFireBase();


        // test autocomplete
        testAutocomplete();





    }



    // 1.menu implement menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //2 - Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }


    //method drawerLayout open close
    @Override
    public void onBackPressed() {

        // 5 - Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    // 2.toolbar implement toolbar method
    private void configureToolbar(){

        // Sets the Toolbar
        setSupportActionBar(toolbar);
    }


    //1.TabLayout create method for implement ViewPager and TabLayout
    private void configureViewPagerAndTabs() {

        //Set Adapter PageAdapter and glue it together
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()));

        // 2 - Glue TabLayout and ViewPager together
        tabs.setupWithViewPager(pager);

        // 3 - Design purpose. Tabs have the same width
        tabs.setTabMode(TabLayout.MODE_FIXED);

        // ADD ICONS
        tabs.getTabAt(0).setIcon(tabIcons[0]);
        tabs.getTabAt(1).setIcon(tabIcons[1]);
        tabs.getTabAt(2).setIcon(tabIcons[2]);

        // CHANGE COLOR ICON TAB LAYOUT
        this.changeColorIconTabLayout();

    }


    // implement button in toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //3 - Handle actions on menu items
        switch (item.getItemId()) {
            case R.id.menu_activity_main_search:

                // START METHOD AUTOCOMPLETE
                //autocompleteIntent();


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //implement button DrawerLayout
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // 4 - Handle Navigation Item Click
        int id = item.getItemId();

        switch (id){

            // ASK RESTAURANT
            case R.id.activity_main_your_lunch :

                // INTENT OF RESTAURANT CHOICE

                // SECURITY DATABASE
                if (FirebaseAuth.getInstance().getCurrentUser() != null){

                    // GET CHOICE CURRENT USER
                    DocumentReference docRef = db.collection("users").document(FirebaseAuth.getInstance().getUid());
                    docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {

                            // DECLARE AND IMPLEMENT USER
                            User user = documentSnapshot.toObject(User.class);

                            // GET CHOICE
                            String choice = user.getChoice();

                            // IF CHOICE EXIST
                            if (choice!=null){

                                // SAVE CHOICE INTO SHARED

                                // IMPLEMENT SHARED PREFERENCES
                                SharedPreferences preferences = getSharedPreferences(MYSHARED, Context.MODE_PRIVATE);

                                // PUT NAME RESTAURANT INTO SHARED
                                preferences.edit().putString(ID_RESTAURANT, choice).commit();
                            }
                        }
                    });

                }

                // START INTENT
                startActivity(new Intent(this, ActivityShowRestaurant.class));
                break;

                // ASK SETTING
            case R.id.activity_main_drawer_settings:
                startActivity(new Intent(this, ActivityNoticationShow.class));
                break;

            case R.id.activity_main_drawer_chat:
                Toast.makeText(MainActivity.this,"c'est ok",Toast.LENGTH_SHORT).show();
                break;

                // ASK LOGOUT
            case R.id.activity_main_drawer_logout:
                Log.e("MainActivity","Logout");
                FirebaseAuth.getInstance().signOut();
                this.launchSignInIntent();
                break;

                default:
                break;
        }

        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }


    // 2 - Configure Drawer Layout
    private void configureDrawerLayout(){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }


    // 3 - Configure NavigationView
    private void configureNavigationView(){
        navigationView.setNavigationItemSelectedListener(this);
    }


    // METHOD FOR CHANGE COLOR OF ICON TAB LAYOUT
    private void changeColorIconTabLayout(){

        // IN START ACTIVITY GET CURRENT COLOR FOR ICON
        tabs.getTabAt(0).getIcon().setColorFilter(Color.parseColor("#ff8a50"), PorterDuff.Mode.SRC_IN);


        // GET CHANGE LISTENER FOR GET PAGE SELECT
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }


            // CURRENT POSITION VIEW PAGER
            @Override
            public void onPageSelected(int position) {

                // IN START ACTIVITY GET CURRENT COLOR FOR ICON
                tabs.getTabAt(0).getIcon().setColorFilter(Color.parseColor("#ff8a50"), PorterDuff.Mode.SRC_IN);


                // CREATE ARRAY WITH COLOR FOR ICON
                String[][] arrayIcon = {{"#ff8a50","#000000","#000000"},{"#000000","#ff8a50","#000000"},{"#000000","#000000","#ff8a50"}};

                // GET COLOR ICONS WITH POSITION VIEW PAGER
                tabs.getTabAt(0).getIcon().setColorFilter(Color.parseColor(arrayIcon[position][0]), PorterDuff.Mode.SRC_IN);
                tabs.getTabAt(1).getIcon().setColorFilter(Color.parseColor(arrayIcon[position][1]), PorterDuff.Mode.SRC_IN);
                tabs.getTabAt(2).getIcon().setColorFilter(Color.parseColor(arrayIcon[position][2]), PorterDuff.Mode.SRC_IN);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    // METHOD FOR IMPLEMENT PROFILE INTO MENU DRAWER
    private void profileIntoDrawerMenu(){

        // SECURITY DATABASE
        if (FirebaseAuth.getInstance().getCurrentUser() != null){

            // IMPLEMENT FIRE BASE STORE
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            // IMPLEMENT USER
            User user = new User();

            // GET PROFILE FROM FIREBASE INTO USER
            //db.collection("users").document("user1").set("username");

            Log.e("USERS","FAILED" + db.collection("users").document("user1"));

            // IMPLEMENT NAVIGATION VIEW
            View headerView = navigationView.getHeaderView(0);

            // IMPLEMENT TEXT VIEW NAME
            TextView textViewName = (TextView)headerView.findViewById(R.id.drawer_nom);

            // IMPLEMENT TEXT VIEW EMAIL
            TextView textViewEmail = (TextView) headerView.findViewById(R.id.drawer_email);

            // IMPLEMENT IMAGE VIEW
            ImageView imageView = (ImageView) headerView.findViewById(R.id.drawer_image);

            // GET NAME INTO STRING
            String stringName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

            // GET EMAIL INTO STRING
            String stringEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

            // SHOW NAME
            textViewName.setText(stringName);

            // SHOW EMAIL
            textViewEmail.setText(stringEmail);

            // ASK IF IMAGE PROFILE IS NOT NULL
            if (FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl() != null){

                // USE GLIDE FOR GET IMAGE PROFILE INTO IMAGE VIEW
                Glide.with(this)
                        .load(FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl())
                        .apply(RequestOptions.circleCropTransform())
                        .into(imageView);
            }
        }

    }


    // CREATE AND LAUNCH SING IN INTENT
    private void launchSignInIntent(){

        // START ACTIVITY FOR RESULT

        // AND ADD LOGO
        startActivityForResult(

                // GET INSTANCE UI
                AuthUI.getInstance()
                        .createSignInIntentBuilder()

                        // ADD PROVIDERS GOOGLE AND FACEBOOK
                        .setAvailableProviders(providers)

                        // ADD LOGO BOL
                        .setLogo(R.drawable.boltitle)

                        // ADD THEME WITH PIC RESTAURANT BACKGROUND
                        .setTheme(R.style.MyThemeUI)
                .build(),
                RC_SIGN_IN);
    }


    // SAVE PROFILE INTO FIREBASE
    private void saveProfileFireBase(){

        // SECURITY DATABASE
        if (FirebaseAuth.getInstance().getCurrentUser() != null){

            // TURN URI TO STRING
            Uri urlPhoto = FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl();
            String stringUri = urlPhoto.toString();

            // ADD DATA

            // CREATE A NEW USER WITH LAST NAME AND PHOTO
            Map<String, Object> user = new HashMap<>();

            // PUT DATA

            //NAME
            user.put("name", FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
            // STRING URL
            user.put("photo",stringUri);
            // PUT  UID
            user.put("uid",FirebaseAuth.getInstance().getCurrentUser().getUid());


            // CREATE DOCUMENT USERS WITH ID PROFILE AUTH
            db.collection("users").document(FirebaseAuth.getInstance().getUid())
                    .set(user, SetOptions.merge())
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                        }
                    });

        }

        }


    // METHOD AUTOCOMPLETE WITH INTENT
    private void autocompleteIntent(){

        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                            .build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }
    }

    // TEST AUTOCOMPLETE
    private void testAutocomplete(){

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                //Log.i(TAG, "Place: " + place.getName());

                String placeName = place.getName().toString();

                Toast.makeText(MainActivity.this,""+placeName, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                //Log.i(TAG, "An error occurred: " + status);
            }
        });
    }

}




















































