package com.example.michel.go4lunch.recyclerview.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michel.go4lunch.R;
import com.example.michel.go4lunch.models.User;
import com.example.michel.go4lunch.recyclerview.ProfileWorkmates;
import com.example.michel.go4lunch.recyclerview.ViewHolderWorkmates;

import java.util.List;

public class AdapterWorkmates extends RecyclerView.Adapter<ViewHolderWorkmates> {



    private List<User> userList;



    // CONSTRUCTOR WITH OBJECT RESTAURANT LIST
    public AdapterWorkmates(List<User> userList) {
        this.userList = userList;
    }


    // METHOD FOR GET POSITION
    public User profileActivity(int position){
        return this.userList.get(position);
    }


    // METHOD WITH LAYOUT INFLATE
    @NonNull
    @Override
    public ViewHolderWorkmates onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_view_workmates, parent, false);
        return new ViewHolderWorkmates(view);
    }


    // METHOD FOR GIVE POSITION
    @Override
    public void onBindViewHolder(@NonNull ViewHolderWorkmates holder, int position) {
        User user = userList.get(position);
        holder.insertDataIntoRecyclerView(user);

    }


    // GET COUNT SIZE
    @Override
    public int getItemCount() {
        return userList.size();
    }
}
