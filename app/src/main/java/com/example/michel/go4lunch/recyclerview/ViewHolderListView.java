package com.example.michel.go4lunch.recyclerview;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.michel.go4lunch.R;
import com.example.michel.go4lunch.models.RestaurantObject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ViewHolderListView extends RecyclerView.ViewHolder {


    // ADD VALUES
    @BindView(R.id.name_recycler_view)TextView name;
    @BindView(R.id.address_recycler_view)TextView address;
    @BindView(R.id.hour_recycler_view)TextView hour;
    @BindView(R.id.type_recycler_view)TextView type;
    @BindView(R.id.star_recycler_view)ImageView star1;
    @BindView(R.id.star_recycler_view2)ImageView star2;
    @BindView(R.id.star_recycler_view3)ImageView star3;
    @BindView(R.id.workmates_recycler_view)TextView workMates;
    @BindView(R.id.distance_recycler_view)TextView distance;
    @BindView(R.id.image_recycler_view)ImageView image;
    @BindView(R.id.icon_workmates_recycler_view)ImageView icon_workmates;





    public ViewHolderListView(View itemView) {
        super(itemView);

        // IMPLEMENT VALUE WITH BUTTER KNIFE AND ITEM VIEW
        ButterKnife.bind(this,itemView);

    }


    // METHOD FOR SET VALUES IN RECYCLER VIEW
    @SuppressLint("ResourceAsColor")
    public void restaurantHolderView(RestaurantObject restaurantObject) {


        // SET NAME
        name.setText(restaurantObject.getName());
        // SET BOLD STYLE
        name.setTypeface(null, Typeface.BOLD);
        // SET TYPE OF FOOD
        type.setText(restaurantObject.getType()+" - ");
        // SET ADDRESS
        address.setText(restaurantObject.getAdresse());

        // IF RESTAURANT CLOSE RED COLOR
        if(restaurantObject.getHeuresOuverture().equals("close")){
            // SET HOUR AND COLOR RED
            hour.setText(restaurantObject.getHeuresOuverture());
            hour.setTextColor(Color.RED);
        }
        else {
            // SET HOUR AND ITALIC STYLE
            hour.setText(restaurantObject.getHeuresOuverture());
            hour.setTypeface(null, Typeface.ITALIC);
        }
        // SET DISTANCE
        distance.setText(String.valueOf(restaurantObject.getDistance())+"m");
        // SET NUMBER WORKMATES AND HIDE IF 0 WITH ICON
        if(restaurantObject.getWorkMates()==0){
            workMates.setVisibility(View.INVISIBLE);
            icon_workmates.setVisibility(View.INVISIBLE);
        }
        workMates.setText("("+String.valueOf(restaurantObject.getWorkMates())+")");
        // CREATE ARRAY FOR SHOW NUMBER OF STAR
        int numberStar = restaurantObject.getStar();
        int[][] invisible = {{View.INVISIBLE,View.INVISIBLE,View.INVISIBLE},
                {View.VISIBLE,View.INVISIBLE,View.INVISIBLE},
                {View.VISIBLE,View.VISIBLE,View.INVISIBLE},
                {View.VISIBLE,View.VISIBLE,View.VISIBLE}};
            star1.setVisibility(invisible[numberStar][0]);
            star2.setVisibility(invisible[numberStar][1]);
            star3.setVisibility(invisible[numberStar][2]);
        // SHOW PIC OF RESTAURANT
        Glide.with(itemView.getContext()).load(restaurantObject.getUrlPhoto()).into(image);



    }

}










































