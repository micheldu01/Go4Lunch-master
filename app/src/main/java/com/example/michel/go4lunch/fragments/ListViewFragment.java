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
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.example.michel.go4lunch.APIMaps.MapStreams;
import com.example.michel.go4lunch.APIMaps.apiPlaceId.GoogleAPIplaceId;
import com.example.michel.go4lunch.ActivityShowRestaurant;
import com.example.michel.go4lunch.BuildConfig;
import com.example.michel.go4lunch.R;
import com.example.michel.go4lunch.models.ObjectRestaurant;
import com.example.michel.go4lunch.models.RestaurantObjectRecycler;
import com.example.michel.go4lunch.models.User;
import com.example.michel.go4lunch.recyclerview.adapter.AdapterListView;
import com.example.michel.go4lunch.recyclerview.ItemClickSupport;
import com.example.michel.go4lunch.recyclerview.adapter.AdapterWorkmates;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListViewFragment extends Fragment {


    // IMPLEMENT RECYCLER VIEW
    @BindView(R.id.list_view_recyclerView)RecyclerView recyclerView;

    // IMPLEMENT REFRESH LAYOUT
    @BindView(R.id.list_view_swipe_refresh)SwipeRefreshLayout refreshLayout;

    // DECLARE LIST RESTAURANT OBJECT
    private List<RestaurantObjectRecycler> restaurantObjectRecyclerList = new ArrayList<>();

    // DECLARE DATA BASE
    FirebaseFirestore db= FirebaseFirestore.getInstance();

    // CREATE ARRAAY LIST WORKMATES
    final ArrayList<String> workmates = new ArrayList<>();

    // URL PHOTO RESTAURANT
    private String url_restaurant;










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
        this.showRestaurantList();






        return view;
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
                showRestaurantList();
            }
        });
    }


    // METHOD FOR GET IMPLEMENT RECYCLER VIEW
    private void showRestaurantList(){

        final int[] count = {0};

        // GET USERS COLLECTION FROM CLOUD
        db.collection("restaurant")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (final DocumentSnapshot document : task.getResult()) {
                                final ObjectRestaurant objectRestaurant = document.toObject(ObjectRestaurant.class);

                                // SPLIT ADDRESS
                                String currentString = objectRestaurant.getAddress();
                                String[] separated = currentString.split(",");

                                // STREET AND VILLAGE
                                final String street = separated[0];
                                final String village;

                                // IF ADDRESS CANT TO BE SEPARATED
                                if (separated.length<2){

                                    // ADD VALUE NULL INTO STRING VILLAGE
                                    village = null;
                                }else {
                                    // IMPLEMENT VILLAGE
                                    village = separated[1];
                                }

                                // GET DATA RESTAURANT FROM GOOGLE API

                                // DECLARE DISPOSABLE WITH STREAM GOOGLE API PLACE ID
                                Disposable disposable = MapStreams.streamGoogleAPIplaceId(BuildConfig.KEY_GOOGLE_MAP, objectRestaurant.getPlace_id())
                                        .subscribeWith(new DisposableObserver<GoogleAPIplaceId>() {
                                            @Override
                                            public void onNext(GoogleAPIplaceId googleAPIplaceId) {


                                                // GET PHOTO RESTAURANT

                                                // IF PHOTO IS NOT NULL GET PHOTO
                                                if (googleAPIplaceId.getResultsAPI().getPhotos() != null) {
                                                    url_restaurant = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=800&photoreference="
                                                            + googleAPIplaceId.getResultsAPI().getPhotos().get(0).getPhotoReference() + "&key=" + BuildConfig.KEY_GOOGLE_MAP;
                                                    Log.e("--photo restaurant--", "--result--" + url_restaurant);





                                                    // ADD DATA INTO OBJECT LIST
                                                    restaurantObjectRecyclerList.add(new RestaurantObjectRecycler(objectRestaurant.getNameRestaurant(),street,village,"jusqu'à 22h", "", objectRestaurant.getRating(),1,150, url_restaurant));

                                                    // IMPLEMENT RECYCLER VIEW
                                                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                                    recyclerView.setAdapter(new AdapterListView(restaurantObjectRecyclerList));

                                                }

                                            }
                                            @Override
                                            public void onError(Throwable e) {
                                            }
                                            @Override
                                            public void onComplete() {
                                            }
                                        });



                            }
                        }
                    }
                });


    }



    // METHOD FOR GET IMPLEMENT RECYCLER VIEW
    private void getNumberWorkmates(){

        final ArrayList<String> number_workmates = new ArrayList<>();

        final int[] number = {0};

        // GET USERS COLLECTION FROM CLOUD
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {

                                // DECLARE AND IMPLEMENT USER
                                User user = document.toObject(User.class);

                                //Log.e("-- list restaurant --", "-- get name restaurant --" + user.getChoice());

                                number_workmates.add(user.getChoice());



                                //Log.e("-- list restaurant --", "-- get name restaurant --" + number_workmates.get(0));


                            }
                        }
                    }
                });

    }

    // METHOD TO ASK IF RESTAURANT IS CHOICE
    private void number(String id_restaurant){


        // ASK DATA BASE, IF ONE USER HAVE CHOICE THIS RESTAURANT
        db.collection("users")
                .whereEqualTo("choice",id_restaurant)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        // IF TASK IS SUCCESS FUL GET DATA
                        if(task.isSuccessful()){

                            // GET DATA FROM DATA BASE
                            for(QueryDocumentSnapshot document : task.getResult()) {

                                // ASK IF CHOICE IS DIFFERENT OF NULL
                                if (!document.getData().equals(null)) {

                                    workmates.add(document.getData().toString());

                                    Log.e("-- list workmates --", "-- get name workmates --" + workmates.size());
                                }
                            }
                        }
                    }
                });
    }
}

































