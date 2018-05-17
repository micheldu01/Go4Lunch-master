package com.example.michel.go4lunch.recyclerview;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.michel.go4lunch.R;
import com.example.michel.go4lunch.auth.ProfileActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolderWorkmates extends RecyclerView.ViewHolder {


    // DECLARE VALUE
    // IMAGE PROFILE
    @BindView(R.id.image_recycler_workmates)ImageView imageProfile;
    // NAME PROFILE
    @BindView(R.id.name_workmates_recycler)TextView nameProfile;
    // TYPE FOOD
    @BindView(R.id.type_workmates_recycler)TextView typeFood;
    // NAME RESTAURANT
    @BindView(R.id.name_restaurant_workmates_recycler)TextView nameRestaurant;




    public ViewHolderWorkmates(View itemView) {
        super(itemView);

        // IMPLEMENT VALUE WITH BUTTER KNIFE AND ITEM VIEW
        ButterKnife.bind(this,itemView);


    }

    // METHOD FOR SET VALUES IN RECYCLER VIEW
    public void insertDataIntoRecyclerView(ProfileActivity profileActivity){

        // SET IMAGE PROFILE
        Glide.with(itemView.getContext()).load(profileActivity.getUrlImage()).into(imageProfile);
        // SET NAME PROFILE
        nameProfile.setText(profileActivity.getName());
        // SET TYPE FOOD
        typeFood.setText(profileActivity.getType());
        // SET NAME RESTAURANT
        nameRestaurant.setText(profileActivity.getName());

    }
}
