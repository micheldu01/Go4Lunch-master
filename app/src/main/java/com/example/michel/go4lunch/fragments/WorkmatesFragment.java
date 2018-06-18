package com.example.michel.go4lunch.fragments;


import android.content.Intent;
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
import com.example.michel.go4lunch.models.User;
import com.example.michel.go4lunch.recyclerview.ProfileWorkmates;
import com.example.michel.go4lunch.recyclerview.adapter.AdapterWorkmates;
import com.example.michel.go4lunch.recyclerview.ItemClickSupport;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkmatesFragment extends Fragment {



    // IMPLEMENT RECYCLER VIEW
    @BindView(R.id.workmates_recycler_view)RecyclerView recyclerView;

    // IMPLEMENT REFRESH LAYOUT
    @BindView(R.id.workmates_swipe_refresh)SwipeRefreshLayout refreshLayout;

    // DECLARE LIST RESTAURANT OBJECT
    private List<ProfileWorkmates> profileActivities = new ArrayList<>();

    // DECLARE LIST RESTAURANT OBJECT
    private ArrayList<User> list_workmate = new ArrayList<>();

    // DECLARE DATA BASE
    FirebaseFirestore db= FirebaseFirestore.getInstance();


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

            // GET USERS COLLECTION FROM CLOUD
            db.collection("users")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            // IF TASK IS SUCCESS FUL GET DATA
                            if (task.isSuccessful()){

                                // GET DATA FROM DATA BASE
                                for (QueryDocumentSnapshot document : task.getResult()){

                                    // ASK IF CHOICE IS DIFFERENT OF NULL
                                    if (!document.getData().equals(null)) {

                                        // SPLIT DATA "="
                                        String currentString = document.getData().toString();
                                        String[] separated = currentString.split("=");

                                        // USERNAME
                                        String username = separated[1];
                                        String[] separated_username = username.split(",");
                                        String username_final = separated_username[0];

                                        // CHOICE
                                        String choice = separated[3];
                                        choice = choice.replace("}","");

                                        // URL PHOTO
                                        String urlPhoto = separated[2];
                                        String[] separated_url = urlPhoto.split(",");
                                        String urlPhoto_final = separated_url[0];

                                        // IMPLEMENT OBJECT USER LIST
                                        list_workmate.add(new User(choice,username_final,urlPhoto_final));


                                        Log.e("---split 1---", " --- ----- " + list_workmate.get(0).getChoice());
                                        Log.e("---split 2---", " --- object user ----- " + list_workmate.get(0).getUsername());
                                        Log.e("---split 2---", " --- object user ----- " + list_workmate.get(0).getUrlPicture());

                                        // IMPLEMENT RECYCLER VIEW
                                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                        recyclerView.setAdapter(new AdapterWorkmates(list_workmate));



                                    }


                                }
                            }

                        }
                    });




/*
        ProfileWorkmates profileWorkmates = new ProfileWorkmates();

        profileActivities.add(new ProfileWorkmates(true,"robert","http://bstatic.ccmbg.com/www.linternaute.com/img/restaurant/villes/440x293/1.jpg","Le bistro"));
        profileActivities.add(new ProfileWorkmates(true,"philippe","http://bstatic.ccmbg.com/www.linternaute.com/img/restaurant/villes/440x293/1.jpg","California"));
        profileActivities.add(new ProfileWorkmates(false,"pierre","http://bstatic.ccmbg.com/www.linternaute.com/img/restaurant/villes/440x293/1.jpg","1"));
        profileActivities.add(new ProfileWorkmates(false,"stéphane","http://bstatic.ccmbg.com/www.linternaute.com/img/restaurant/villes/440x293/1.jpg","1"));
        profileActivities.add(new ProfileWorkmates(true,"stéphanie","http://bstatic.ccmbg.com/www.linternaute.com/img/restaurant/villes/440x293/1.jpg","Embassade d'asie"));
        profileActivities.add(new ProfileWorkmates(false,"Annie","http://bstatic.ccmbg.com/www.linternaute.com/img/restaurant/villes/440x293/1.jpg","1"));


        // SORT TYPE RESTAURANT
            Collections.sort(profileActivities);




        // IMPLEMENT RECYCLER VIEW
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new AdapterWorkmates(profileActivities));

*/
    }

    // MEHTOD CLICK ON RECYCLER VIEW
    private void configureOnClickRecyclerView() {
        ItemClickSupport.addTo(recyclerView, R.layout.fragment_workmates)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                        // IF CHOICE OF RESTAURANT IS READY GET INTENT
                        if(profileActivities.get(position).isChoice()==true){
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
                // SHOW LIST RESTAURANT

            }
        });
    }

}
