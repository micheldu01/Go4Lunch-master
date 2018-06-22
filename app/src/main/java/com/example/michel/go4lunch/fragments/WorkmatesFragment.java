package com.example.michel.go4lunch.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michel.go4lunch.ActivityShowRestaurant;
import com.example.michel.go4lunch.R;
import com.example.michel.go4lunch.models.ObjectRestaurant;
import com.example.michel.go4lunch.models.User;
import com.example.michel.go4lunch.recyclerview.adapter.AdapterWorkmates;
import com.example.michel.go4lunch.recyclerview.ItemClickSupport;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.michel.go4lunch.shared.Shared.ID_RESTAURANT;
import static com.example.michel.go4lunch.shared.Shared.MYSHARED;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkmatesFragment extends Fragment {



    // IMPLEMENT RECYCLER VIEW
    @BindView(R.id.workmates_recycler_view)RecyclerView recyclerView;

    // IMPLEMENT REFRESH LAYOUT
    @BindView(R.id.workmates_swipe_refresh)SwipeRefreshLayout refreshLayout;

    // DECLARE LIST RESTAURANT OBJECT
    private ArrayList<User> list_workmate = new ArrayList<>();

    // DECLARE DATA BASE
    FirebaseFirestore db= FirebaseFirestore.getInstance();

    // DECLARE SHARED PREFERENCES
    private SharedPreferences preferences;

    // STRING CHOICE
    private String choice;

    // DECLARE NAME RESTAURANT
    private String name_restaurant;

    // DECLARE USERNAME WORKMATES
    private String username;

    // DECLARE URL PHOTO
    private String url_photo;

    // DECLARE UID
    private String uid;



    //create constructor
    public static WorkmatesFragment newInstance() {
        // Required empty public constructor
        return (new WorkmatesFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workmates, container, false);

        // DECLARE BUTTER KNIFE
        ButterKnife.bind(this,view);

        // SWIPE REFRESH
        this.configureSwipeRefreshLayout();

        // DECLARE THE ONCLICK FOR USE IT FOR SHOW VIEW RESTAUARANT LIST
        this.configureOnClickRecyclerView();

        // SHOW LIST PROFILES
        this.showProfileWithChoice();




        return view;
    }

    // METHOD SHOW PROFILE CHOICE
    private void showProfileWithChoice(){

        // GET OBJECT USERS FROM CLOUD FIRESTORE

        // SECURITY DATABASE
        if (FirebaseAuth.getInstance().getCurrentUser() != null){

            // GET USERS COLLECTION FROM CLOUD
            db.collection("users")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            // IF TASK IS SUCCESS FUL GET DATA
                            if (task.isSuccessful()) {

                                // GET DATA FROM DATA BASE
                                //for (QueryDocumentSnapshot document : task.getResult()){
                                for (DocumentSnapshot document : task.getResult()) {
                                    final User user = document.toObject(User.class);



                                    // IMPLEMENT USERNAME
                                    username = user.getName();

                                    Log.e("--test workmates --", "-- uid --" + user.getUid());
                                    Log.e("--test workmates --", "-- username --" + user.getName());
                                    Log.e("--test workmates --", "-- choice --" + user.getChoice());
                                    Log.e("--test workmates --", "-- url_picture --" + user.getPhoto());
                                    Log.e("--test workmates --", "-- name_restaurant --" + user.getName_restaurant());



                                    // IMPLEMENT NAME RESTAURANT
                                    name_restaurant = user.getName_restaurant();

                                    // IMPLEMENT URL PHOTO
                                    url_photo = user.getPhoto();

                                    // IMPLEMENT CHOICE
                                    choice = user.getChoice();

                                    // GET UID
                                    uid = user.getUid();


                                    // DON'T ADD CURRENT WORKMATE
                                    if (!FirebaseAuth.getInstance().getUid().equals(uid)) {

                                        // IMPLEMENT OBJECT USER LIST
                                        list_workmate.add(new User(choice, username, url_photo, name_restaurant));
                                    }

                                    // SORT NAME RESTAURANT
                                    Collections.sort(list_workmate, new Comparator<User>() {
                                        @Override
                                        public int compare(User user, User t1) {

                                            // COMPARE TO NAME RESTAURANT
                                            return user.getName_restaurant().compareTo(t1.getName_restaurant());
                                        }
                                    });


                                    // IMPLEMENT RECYCLER VIEW
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                    recyclerView.setAdapter(new AdapterWorkmates(list_workmate));
                                }
                            }
                        }

                    });
        }

    }


    // MEHTOD CLICK ON RECYCLER VIEW
    private void configureOnClickRecyclerView() {
        ItemClickSupport.addTo(recyclerView, R.layout.fragment_workmates)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                        // IF CHOICE OF RESTAURANT IS READY GET INTENT
                        if(list_workmate.get(position).getChoice()!=null){

                            // SAVE CHOICE WORKMATES FOR CLICK INTENT
                            preferences = getActivity().getSharedPreferences(MYSHARED, Context.MODE_PRIVATE);

                            // SAVE CHOICE INTO SHARED
                            preferences.edit().putString(ID_RESTAURANT,list_workmate.get(position).getChoice()).commit();

                            // ON CLICK INTENT
                            startActivity(new Intent(getContext(),ActivityShowRestaurant.class));
                        }

                    }
                });
    }


    // SWIPE REFRESH METHOD
    private void configureSwipeRefreshLayout() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // REMOVE LIST WORKMATE
                list_workmate.clear();

                // SHOW LIST RESTAURANT
                showProfileWithChoice();
            }
        });
    }

}









































