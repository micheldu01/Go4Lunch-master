package com.example.michel.go4lunch.recyclerview;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.michel.go4lunch.R;
import com.example.michel.go4lunch.models.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolderWorkmates extends RecyclerView.ViewHolder {


    // DECLARE VALUE

    // IMAGE PROFILE
    @BindView(R.id.image_recycler_workmates)ImageView imageProfile;

    // NAME PROFILE
    @BindView(R.id.name_workmates_recycler)TextView nameProfile;

    // NAME RESTAURANT
    @BindView(R.id.name_restaurant_workmates_recycler)TextView nameRestaurant;

    // LINE
    @BindView(R.id.line_workmates_recycler)TextView line;

    // TEXT EAT OR NOT DECIDED
    @BindView(R.id.decide_workmates_recycler)TextView eating;





    public ViewHolderWorkmates(View itemView) {
        super(itemView);

        // IMPLEMENT VALUE WITH BUTTER KNIFE AND ITEM VIEW
        ButterKnife.bind(this,itemView);


    }

    // METHOD FOR SET VALUES IN RECYCLER VIEW
    @SuppressLint("ResourceAsColor")
    public void insertDataIntoRecyclerView(User user){


        // IF CHOICE RESTAURANT
        if(user.getChoice()!=null){

            // SET NAME
            nameProfile.setText(user.getUsername()+" ");

            // SET IMAGE PROFILE
            Glide.with(itemView.getContext()).load(user.getUrlPicture()).apply(RequestOptions.circleCropTransform()).into(imageProfile);

            // SET NAME RESTAURANT
            nameRestaurant.setText(" : "+ user.getName_restaurant());

        }else{

            // SET NAME PROFILE
            nameProfile.setText(user.getUsername()+" ");

            // ITALIC
            nameProfile.setTypeface(null, Typeface.ITALIC);

            // COLOR GRAY LIGHT
            nameProfile.setTextColor(Color.parseColor("#D3D3D3"));

            // CHANGE TEXT NO DECIDED
            eating.setText(R.string.no_decided);

            // ITALIC
            eating.setTypeface(null, Typeface.ITALIC);

            // COLOR GRAY LIGHT
            eating.setTextColor(Color.parseColor("#D3D3D3"));

            // SET IMAGE PROFILE
            Glide.with(itemView.getContext()).load(user.getUrlPicture()).apply(RequestOptions.circleCropTransform()).into(imageProfile);

        }



    }


}
