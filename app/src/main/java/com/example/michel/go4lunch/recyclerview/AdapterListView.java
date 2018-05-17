package com.example.michel.go4lunch.recyclerview;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michel.go4lunch.R;

import java.util.List;


public class AdapterListView extends RecyclerView.Adapter<ViewHolderListView>{


    private List<RestaurantObject> restaurantObjectList;



    // CONSTRUCTOR WITH OBJECT RESTAURANT LIST
    public AdapterListView(List<RestaurantObject> restaurantObjectList) {
        this.restaurantObjectList = restaurantObjectList;
    }


    // METHOD FOR GET POSITION
    public RestaurantObject restaurantObject(int position){
        return this.restaurantObjectList.get(position);
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
        RestaurantObject restaurantObject = restaurantObjectList.get(position);
        holder.restaurantHolderView(restaurantObject);

    }


    // GET COUNT SIZE
    @Override
    public int getItemCount() {
        return restaurantObjectList.size();
    }
}
