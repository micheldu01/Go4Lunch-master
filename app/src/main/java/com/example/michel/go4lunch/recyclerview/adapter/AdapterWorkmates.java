package com.example.michel.go4lunch.recyclerview.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michel.go4lunch.R;
import com.example.michel.go4lunch.recyclerview.ProfileWorkmates;
import com.example.michel.go4lunch.recyclerview.ViewHolderWorkmates;

import java.util.List;

public class AdapterWorkmates extends RecyclerView.Adapter<ViewHolderWorkmates> {



    private List<ProfileWorkmates> profileActivitiesList;



    // CONSTRUCTOR WITH OBJECT RESTAURANT LIST
    public AdapterWorkmates(List<ProfileWorkmates> profileActivitiesList) {
        this.profileActivitiesList = profileActivitiesList;
    }


    // METHOD FOR GET POSITION
    public ProfileWorkmates profileActivity(int position){
        return this.profileActivitiesList.get(position);
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
        ProfileWorkmates profileWorkmates = profileActivitiesList.get(position);
        holder.insertDataIntoRecyclerView(profileWorkmates);

    }


    // GET COUNT SIZE
    @Override
    public int getItemCount() {
        return profileActivitiesList.size();
    }
}
