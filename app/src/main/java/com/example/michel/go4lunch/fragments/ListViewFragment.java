package com.example.michel.go4lunch.fragments;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michel.go4lunch.ActivityShowRestaurant;
import com.example.michel.go4lunch.R;
import com.example.michel.go4lunch.recyclerview.AdapterListView;
import com.example.michel.go4lunch.recyclerview.ItemClickSupport;
import com.example.michel.go4lunch.recyclerview.RestaurantObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListViewFragment extends Fragment {


    // IMPLEMENT RECYCLER VIEW
    @BindView(R.id.list_view_recyclerView)RecyclerView recyclerView;

    // IMPLEMENT REFRESH LAYOUT
    @BindView(R.id.list_view_swipe_refresh)SwipeRefreshLayout refreshLayout;

    // DECLARE LIST RESTAURANT OBJECT
    private List<RestaurantObject> restaurantObjectList = new ArrayList<>();





    //create constructor
    public static ListViewFragment newInstance() {
        // Required empty public constructor
        return (new ListViewFragment());
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);

        // DECLARE BUTTER KNIFE
        ButterKnife.bind(this,view);

        // SWIPE REFRESH
        this.configureSwipeRefreshLayout();

        // DECLARE THE ONCLICK FOR USE IT FOR SHOW VIEW RESTAUARANT LIST
        this.configureOnClickRecyclerView();

        // SHOW RESTAURANT LIST
        this.showListRestaurant();


        return view;
    }

    // METHOD FOR SET LIST RESTAURANT INTO RECYCLER VIEW
    private void showListRestaurant() {


        // ADD DATA INTO OBJECT LIST
        restaurantObjectList.add(new RestaurantObject("Mon restaurant 1","french","Mon adresse", "jusqu'à 22h", 150,1,0, "http://bstatic.ccmbg.com/www.linternaute.com/img/restaurant/villes/440x293/1.jpg"));
        restaurantObjectList.add(new RestaurantObject("Mon restaurant 2","french","Mon adresse", "jusqu'à 22h", 140,1,2,null));
        restaurantObjectList.add(new RestaurantObject("Mon restaurant 3","french","Mon adresse", "jusqu'à 22h", 300,3,1, "http://bstatic.ccmbg.com/www.linternaute.com/img/restaurant/villes/440x293/1.jpg"));
        restaurantObjectList.add(new RestaurantObject("Mon restaurant 4","french","Mon adresse", "close", 520,0,3, "http://bstatic.ccmbg.com/www.linternaute.com/img/restaurant/villes/440x293/1.jpg"));
        restaurantObjectList.add(new RestaurantObject("Mon restaurant 5","french","Mon adresse", "jusqu'à 22h", 600,0,2, "http://bstatic.ccmbg.com/www.linternaute.com/img/restaurant/villes/440x293/1.jpg"));
        restaurantObjectList.add(new RestaurantObject("Mon restaurant 6","french","Mon adresse", "jusqu'à 22h", 200,1,0, "http://bstatic.ccmbg.com/www.linternaute.com/img/restaurant/villes/440x293/1.jpg"));
        restaurantObjectList.add(new RestaurantObject("Mon restaurant 7","french","Mon adresse", "jusqu'à 22h", 300,4,3, "http://bstatic.ccmbg.com/www.linternaute.com/img/restaurant/villes/440x293/1.jpg"));


        // SORT DISTANCE FROM THE SMALLEST TO THE LARGEST
        Collections.sort(restaurantObjectList);


        // IMPLEMENT RECYCLER VIEW
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new AdapterListView(restaurantObjectList));

    }


    // MEHTOD CLICK ON RECYCLER VIEW
    private void configureOnClickRecyclerView() {
        ItemClickSupport.addTo(recyclerView, R.layout.fragment_list_view)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                        // ON CLICK INTENT
                        startActivity(new Intent(getContext(),ActivityShowRestaurant.class));

                    }
                });

    }


    // SWIPE REFRESH METHOD
    private void configureSwipeRefreshLayout() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // SHOW LIST RESTAURANT
                showListRestaurant();
            }
        });
    }


}

































