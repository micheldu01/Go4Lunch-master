package com.example.michel.go4lunch;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.michel.go4lunch.recyclerview.AdapterShowRestaurant;
import com.example.michel.go4lunch.recyclerview.AdapterWorkmates;
import com.example.michel.go4lunch.recyclerview.ChoiceRestaurant;
import com.example.michel.go4lunch.recyclerview.ProfileWorkmates;
import com.example.michel.go4lunch.recyclerview.RestaurantObject;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityShowRestaurant extends AppCompatActivity {


    // DECLARE RECYCLER VIEW
    @BindView(R.id.show_restaurant_recycler_view)RecyclerView recyclerView;
    // DECLARE LIST RESTAURANT OBJECT
    private List<ChoiceRestaurant> choiceRestaurantList = new ArrayList<>();
    // DECLARE BUTTON CALL
    @BindView(R.id.image_button_phone_show_restaurant)ImageButton imageButtonCall;
    // DECLARE BUTTON LIKE
    @BindView(R.id.image_button_star_show_restaurant)ImageButton imageButtonLike;
    // DECLARE BUTTON WEBSITE
    @BindView(R.id.image_button_public_show_restaurant)ImageButton imageButtonWeb;
    // DECLARE BUTTON GREEN
    @BindView(R.id.button_green_show_restaurant)ImageView imageViewButtonGreen;
    // DECLARE BUTTON RED
    @BindView(R.id.button_red_show_restaurant)ImageView imageViewButtonRed;




    // DECLARE LIST RESTAURANT OBJECT
    private List<RestaurantObject> restaurantObjectList = new ArrayList<>();


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


        // TEST RECYCLER VIEW-------------------------------------------
        choiceRestaurantList.add(new ChoiceRestaurant("robert","http://bstatic.ccmbg.com/www.linternaute.com/img/restaurant/villes/440x293/1.jpg"));
        choiceRestaurantList.add(new ChoiceRestaurant("robert","http://bstatic.ccmbg.com/www.linternaute.com/img/restaurant/villes/440x293/1.jpg"));
        choiceRestaurantList.add(new ChoiceRestaurant("robert","http://bstatic.ccmbg.com/www.linternaute.com/img/restaurant/villes/440x293/1.jpg"));
        choiceRestaurantList.add(new ChoiceRestaurant("robert","http://bstatic.ccmbg.com/www.linternaute.com/img/restaurant/villes/440x293/1.jpg"));
        choiceRestaurantList.add(new ChoiceRestaurant("robert","http://bstatic.ccmbg.com/www.linternaute.com/img/restaurant/villes/440x293/1.jpg"));
        choiceRestaurantList.add(new ChoiceRestaurant("robert","http://bstatic.ccmbg.com/www.linternaute.com/img/restaurant/villes/440x293/1.jpg"));
        // IMPLEMENT RECYCLER VIEW
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AdapterShowRestaurant(choiceRestaurantList));
        //------------------------------------------------------------------











    }

    // METHOD BACKGROUND LAYOUT PARAMS
    private void colorGray(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.parseColor("#808080"));
        }

    }




    // IMPLEMENT BUTTON

        // BUTTON CALL
        @OnClick(R.id.image_button_phone_show_restaurant)
        void submitImageBuutonCall (View view){

            // TOAST IF CLICK
            Toast.makeText(this, "-----TEST CALL----", Toast.LENGTH_SHORT).show();
        }

        // BUTTON CALL
        @OnClick(R.id.image_button_star_show_restaurant)
        void submitImageBuutonLike(View view){

            // TOAST IF CLICK
            Toast.makeText(this,"-----TEST LIKE----", Toast.LENGTH_SHORT).show();
        }

        // BUTTON CALL
        @OnClick(R.id.image_button_public_show_restaurant)
        void submitImageBuutonWeb(View view){

            // TOAST IF CLICK
            Toast.makeText(this,"-----TEST WEB----", Toast.LENGTH_SHORT).show();
        }



        // BUTTON CALL
        @OnClick(R.id.button_green_show_restaurant)
        void submitImageViewButtonGreen(View view){

             // TOAST IF CLICK
             Toast.makeText(this,"-----BUTTON GREEN----", Toast.LENGTH_SHORT).show();
            imageViewButtonGreen.setVisibility(view.INVISIBLE);

        }

        // BUTTON CALL
        @OnClick(R.id.button_red_show_restaurant)
        void submitImageViewButtonRed(View view){

        // TOAST IF CLICK
        Toast.makeText(this,"-----BUTTON RED----", Toast.LENGTH_SHORT).show();
        imageViewButtonRed.setVisibility(view.INVISIBLE);

    }


}



















































