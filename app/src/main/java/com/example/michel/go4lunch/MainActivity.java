package com.example.michel.go4lunch;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.nfc.Tag;
import android.support.annotation.NonNull;
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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michel.go4lunch.adapter.PageAdapter;
import com.example.michel.go4lunch.auth.AuthActivity;
import com.example.michel.go4lunch.base.BaseActivity;
import com.example.michel.go4lunch.models.User;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.firebase.ui.auth.ui.email.CheckEmailFragment.TAG;

public class MainActivity extends BaseActivity
        implements
        NavigationView.OnNavigationItemSelectedListener{



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


    // ADD ARRAY ICONS
    private int[] tabIcons = {R.drawable.ic_map_black_24dp,R.drawable.ic_view_list_black_24dp,R.drawable.ic_group_black_24dp};

    // MENU DRAWER
    private NavigationView navView;
    private TextView textViewName;

    // FIRE BASE
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DECLARE BUTTER KNIFE
        ButterKnife.bind(this);



        // METHOD FOR AUTH
        this.methodLogged();

        // 1.toolbar add toolbar method
        this.configureToolbar();

        //TabLayout execute tabLayout
        this.configureViewPagerAndTabs();

        //NavigationView call method
        this.configureNavigationView();

        //DrawerLayout call method
        this.configureDrawerLayout();

        // DRAWER MENU PROFILE
        this.methodDrawerMenu();



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
                //add toast for click response
                Toast.makeText(this, "Recherche indisponible, demandez plutôt l'avis de Google, c'est mieux et plus rapide.", Toast.LENGTH_LONG).show();
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
            case R.id.activity_main_drawer_news :
                break;
                // ASK SETTING
            case R.id.activity_main_drawer_settings:
                break;
                // ASK LOGOUT
            case R.id.activity_main_drawer_logout:
                Log.e("MainActivity","Logout");
                LoginManager.getInstance().logOut();
                startActivity(new Intent(MainActivity.this,AuthActivity.class));

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


    // METHOD FOR IMPLEMENT MENU DRAWER
    private void methodDrawerMenu(){

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        User user = new User();

        View headerView = navigationView.getHeaderView(0);
        TextView textViewName = (TextView)headerView.findViewById(R.id.drawer_nom);
        textViewName.setText("laurent");

    }


    // METHOD FOR LOGGED OR NOT
    private void methodLogged(){

        // ASK IF IS IT NOT LOGGED
        // WITH FACEBOOK
        if (AccessToken.getCurrentAccessToken() == null){

           // IF NOT CONNECT START INTENT FOR GO TO AUTH ACTIVITY
            startActivity(new Intent(MainActivity.this, AuthActivity.class));

        }
    }
}






































