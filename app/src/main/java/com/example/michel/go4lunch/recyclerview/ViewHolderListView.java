package com.example.michel.go4lunch.recyclerview;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.michel.go4lunch.R;

import java.text.ParseException;

import butterknife.BindView;


public class ViewHolderListView extends RecyclerView.ViewHolder {


    // ADD VALUES
    @BindView(R.id.name_recycler_view)TextView name;
    @BindView(R.id.address_recycler_view)TextView address;
    @BindView(R.id.hour_recycler_view)TextView hour;
    @BindView(R.id.type_recycler_view)TextView type;
    //@BindView(R.id.star_recycler_view)ImageView star;
    private TextView star;
    @BindView(R.id.workmates_recycler_view)TextView workMates;
    @BindView(R.id.distance_recycler_view)TextView distance;
    @BindView(R.id.image_recycler_view)ImageView image;


    public ViewHolderListView(View itemView) {
        super(itemView);

    }

    public void restaurantHolderView(RestaurantObject restaurantObject) {

        name.setText(restaurantObject.getName());
        address.setText(" - " + restaurantObject.getAdresse());
        hour.setText(restaurantObject.getHeuresOuverture());
        type.setText(restaurantObject.getType());
        star.setText("2");
        workMates.setText(restaurantObject.getWorkMates());
        distance.setText(restaurantObject.getDistance());
        Glide.with(itemView.getContext()).load(restaurantObject.getUrlPhoto()).into(image);

    }


}










































