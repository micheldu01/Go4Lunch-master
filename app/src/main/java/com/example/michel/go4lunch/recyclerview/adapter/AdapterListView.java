package com.example.michel.go4lunch.recyclerview.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michel.go4lunch.R;
import com.example.michel.go4lunch.models.RestaurantObjectRecycler;
import com.example.michel.go4lunch.recyclerview.ViewHolderListView;

import java.util.List;


public class AdapterListView extends RecyclerView.Adapter<ViewHolderListView>{


    private List<RestaurantObjectRecycler> restaurantObjectRecyclerList;



    // CONSTRUCTOR WITH OBJECT RESTAURANT LIST
    public AdapterListView(List<RestaurantObjectRecycler> restaurantObjectRecyclerList) {
        this.restaurantObjectRecyclerList = restaurantObjectRecyclerList;
    }


    // METHOD FOR GET POSITION
    public RestaurantObjectRecycler restaurantObject(int position){
        return this.restaurantObjectRecyclerList.get(position);
    }


    // METHOD WITH LAYOUT INFLATE
    @NonNull
    @Override
    public ViewHolderListView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_view_list_view, parent, false);
        return new ViewHolderListView(view);
    }



    // METHOD FOR GIVE POSITION
    @Override
    public void onBindViewHolder(@NonNull ViewHolderListView holder, int position) {
        RestaurantObjectRecycler restaurantObjectRecycler = restaurantObjectRecyclerList.get(position);
        holder.restaurantHolderView(restaurantObjectRecycler);
    }


    // GET COUNT SIZE
    @Override
    public int getItemCount() {
        return restaurantObjectRecyclerList.size();
    }
}
