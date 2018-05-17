package com.example.michel.go4lunch.recyclerview;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michel.go4lunch.R;
import com.example.michel.go4lunch.auth.ProfileActivity;

import java.util.List;

public class AdapterWorkmates extends RecyclerView.Adapter<ViewHolderWorkmates> {



    private List<ProfileActivity> profileActivitiesList;



    // CONSTRUCTOR WITH OBJECT RESTAURANT LIST
    public AdapterWorkmates(List<ProfileActivity> profileActivitiesList) {
        this.profileActivitiesList = profileActivitiesList;
    }


    // METHOD FOR GET POSITION
    public ProfileActivity profileActivity(int position){
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
        ProfileActivity profileActivity = profileActivitiesList.get(position);
        holder.insertDataIntoRecyclerView(profileActivity);

    }


    // GET COUNT SIZE
    @Override
    public int getItemCount() {
        return profileActivitiesList.size();
    }
}
