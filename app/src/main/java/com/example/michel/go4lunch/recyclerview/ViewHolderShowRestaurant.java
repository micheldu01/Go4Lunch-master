package com.example.michel.go4lunch.recyclerview;


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

public class ViewHolderShowRestaurant extends RecyclerView.ViewHolder {


    // DECLARE VALUES

    // IMAGE VIEW
    @BindView(R.id.image_recycler_show_restaurant)ImageView imageView;

    // NAME PROFILE
    @BindView(R.id.name_restaurant_show_restaurant)TextView name;

    // STRING IS JOINING
    @BindView(R.id.string_show_restaurant_recycler)TextView isjoining;



    public ViewHolderShowRestaurant(View itemView) {
        super(itemView);

        // IMPLEMENT VALUE WITH BUTTER KNIFE AND ITEM VIEW
        ButterKnife.bind(this,itemView);
    }


    // GET DATA INTO RECYCLER VIEW
    public void restaurantShowRecyclerView(User user){

        // SET NAME
        name.setText(user.getUsername()+" ");
        // SET IMAGE
        Glide.with(itemView.getContext()).load(user.getUrlPicture()).apply(RequestOptions.circleCropTransform()).into(imageView);

    }



}





