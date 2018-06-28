package com.example.michel.go4lunch.recyclerview;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.michel.go4lunch.R;
import com.example.michel.go4lunch.models.RestaurantObjectRecycler;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ViewHolderListView extends RecyclerView.ViewHolder {


    // ADD VALUES

    // NAME
    @BindView(R.id.name_recycler_view)TextView name;

    // ADDRESS (STREET)
    @BindView(R.id.address_recycler_view)TextView address;

    // HOUR
    @BindView(R.id.hour_recycler_view)TextView hour;

    // VILLAGE
    @BindView(R.id.village_recycler_view)TextView village;

    // WORKMATES
    @BindView(R.id.workmates_recycler_view)TextView workMates;

    // DISTANCE
    @BindView(R.id.distance_recycler_view)TextView distance;

    // IMAGE
    @BindView(R.id.image_recycler_view)ImageView image;

    // IMAGE VIEW ICON WORKMATES
    @BindView(R.id.icon_workmates_recycler_view)ImageView icon_workmates;

    // STAR RATING BAR
    @BindView(R.id.rating_bar)RatingBar ratingBar;





    public ViewHolderListView(View itemView) {
        super(itemView);

        // IMPLEMENT VALUE WITH BUTTER KNIFE AND ITEM VIEW
        ButterKnife.bind(this,itemView);

    }


    // METHOD FOR SET VALUES IN RECYCLER VIEW
    @SuppressLint("ResourceAsColor")
    public void restaurantHolderView(RestaurantObjectRecycler restaurantObjectRecycler) {


        // SET NAME
        name.setText(restaurantObjectRecycler.getName());

        // SET BOLD STYLE
        name.setTypeface(null, Typeface.BOLD);

        // SET ADDRESS
        address.setText(restaurantObjectRecycler.getAddress());

        // VILLAGE
        village.setText(restaurantObjectRecycler.getVillage());

        // IF RESTAURANT CLOSE RED COLOR
        if(restaurantObjectRecycler.getHeuresOuverture().equals("close")){

            // SET HOUR AND COLOR RED
            hour.setText(restaurantObjectRecycler.getHeuresOuverture());
            hour.setTextColor(Color.RED);
        }
        else {
            // SET HOUR AND ITALIC STYLE
            hour.setText(restaurantObjectRecycler.getHeuresOuverture());
            hour.setTextColor(Color.BLACK);
            hour.setTypeface(null, Typeface.ITALIC);
        }

        // SET DISTANCE
        distance.setText(String.valueOf(restaurantObjectRecycler.getDistance())+"m");


        // SET NUMBER WORKMATES AND HIDE IF 0 WITH ICON
        if(restaurantObjectRecycler.getWorkmates()==0){

            // MAKE WORKMATES INVISIBLE
            workMates.setVisibility(View.INVISIBLE);

            // MAKE ICON WORKMATES INVISIBLE
            icon_workmates.setVisibility(View.INVISIBLE);
        }

        Log.e("--workmates IF --","-- number -- " + restaurantObjectRecycler.getWorkmates());

        // IMPLEMENT NUMBER WORKMATES
        workMates.setText("("+String.valueOf(restaurantObjectRecycler.getWorkmates())+")");


        // SHOW RATING BAR
        ratingBar.setRating((float) restaurantObjectRecycler.getStar());

        // SHOW PIC OF RESTAURANT

        // IMPLEMENT PHOTO RESTAURANT IF PHOTO EXIST
        if(restaurantObjectRecycler.getUrlPhoto()!=null){

            // IMPLEMENT PHOTO RESTAURANT INTO IMAGE VIEW
            Glide.with(itemView.getContext()).load(restaurantObjectRecycler.getUrlPhoto()).into(image);
        }else {

            // IMPLEMENT PHOTO RESTAURANT INTO IMAGE VIEW
            Glide.with(itemView.getContext()).load(R.drawable.sign_no_camera2).into(image);
        }
    }

}










































