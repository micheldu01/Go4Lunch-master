package com.example.michel.go4lunch.recyclerview.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michel.go4lunch.R;
import com.example.michel.go4lunch.recyclerview.ChoiceRestaurant;
import com.example.michel.go4lunch.recyclerview.ViewHolderShowRestaurant;

import java.util.List;

public class AdapterShowRestaurant extends RecyclerView.Adapter<ViewHolderShowRestaurant> {


    private List<ChoiceRestaurant> choiceRestaurantList;



    // CONSTRUCTOR WITH OBJECT RESTAURANT LIST
    public AdapterShowRestaurant(List<ChoiceRestaurant> choiceRestaurantList) {
        this.choiceRestaurantList = choiceRestaurantList;
    }


    // METHOD FOR GET POSITION
    public ChoiceRestaurant choiceRestaurant(int position){
        return this.choiceRestaurantList.get(position);
    }


    // METHOD WITH LAYOUT INFLATE
    @NonNull
    @Override
    public ViewHolderShowRestaurant onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_view_show_restaurant, parent, false);
        return new ViewHolderShowRestaurant(view);
    }


    // METHOD FOR GIVE POSITION
    @Override
    public void onBindViewHolder(@NonNull ViewHolderShowRestaurant holder, int position) {
        ChoiceRestaurant choiceRestaurant = choiceRestaurantList.get(position);
        holder.restaurantShowRecyclerView(choiceRestaurant);

    }


    // GET COUNT SIZE
    @Override
    public int getItemCount() {
        return choiceRestaurantList.size();
    }
}
