package com.example.michel.go4lunch;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
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
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.michel.go4lunch.adapter.PageAdapter;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DECLARE BUTTER KNIFE
        ButterKnife.bind(this);




        //startActivity(new Intent(MainActivity.this,AuthActivity.class));



        // 1.toolbar add toolbar method
        this.configureToolbar();

        //TabLayout execute tabLayout
        this.configureViewPagerAndTabs();

        //NavigationView call method
        this.configureNavigationView();

        //DrawerLayout call method
        this.configureDrawerLayout();


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


        //tabs.getTabAt(0).getIcon().setColorFilter(Color.parseColor("#ff8a50"), PorterDuff.Mode.SRC_IN);




        // GET CURRENT ITEM FOR CHANGE COLOR
        int i = pager.getCurrentItem();
        Log.e("MainActivity","recup current item ////////////////////////////////////////" + i);

        // ARRAY COLORS ORANGE AND BLACK
        String[][] arrayIcon = {{"#ff8a50","#000000","#000000"},{"#000000","#ff8a50","#000000"},{"#000000","#000000","#ff8a50"}};

        // CHANGE COLOR ICONS
        //tabs.getTabAt(i).getIcon().setColorFilter(Color.parseColor(arrayIcon[i][0]), PorterDuff.Mode.SRC_IN);
        //tabs.getTabAt(i).getIcon().setColorFilter(Color.parseColor(arrayIcon[i][1]), PorterDuff.Mode.SRC_IN);
        //tabs.getTabAt(i).getIcon().setColorFilter(Color.parseColor(arrayIcon[i][2]), PorterDuff.Mode.SRC_IN);

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
            case R.id.activity_main_drawer_news :
                break;
            case R.id.activity_main_drawer_profile:
                break;
            case R.id.activity_main_drawer_settings:
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

    // CHANGE COLOR ICON INTO TAB LAYOUT
    private void changeColorIconTabLayout(){


    }

}






































