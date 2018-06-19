package com.example.michel.go4lunch.recyclerview.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michel.go4lunch.R;
import com.example.michel.go4lunch.models.User;
import com.example.michel.go4lunch.recyclerview.ChoiceRestaurant;
import com.example.michel.go4lunch.recyclerview.ViewHolderShowRestaurant;

import java.util.List;

public class AdapterShowRestaurant extends RecyclerView.Adapter<ViewHolderShowRestaurant> {


    private List<User> userList;



    // CONSTRUCTOR WITH OBJECT RESTAURANT LIST
    public AdapterShowRestaurant(List<User> userList) {
        this.userList = userList;
    }


    // METHOD FOR GET POSITION
    public User user(int position){
        return this.userList.get(position);
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
        User user = userList.get(position);
        holder.restaurantShowRecyclerView(user);

    }


    // GET COUNT SIZE
    @Override
    public int getItemCount() {
        return userList.size();
    }
}

































