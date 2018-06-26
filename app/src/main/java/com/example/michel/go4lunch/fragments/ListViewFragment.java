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
import android.widget.RatingBar;
import android.widget.Toast;

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
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import static com.example.michel.go4lunch.shared.Shared.ID_RESTAURANT;
import static com.example.michel.go4lunch.shared.Shared.MYSHARED;


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

    // DECLARE CURRENT DAY
    private int day = 0;

    // DECLARE INT WORKMATES
    private int number;

    // DECLARE SHARED PREFERENCES
    private SharedPreferences preferences;





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





    // SWIPE REFRESH METHOD
    private void configureSwipeRefreshLayout() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // REMOVE RESTAURANT OBJECT LIST
                restaurantObjectRecyclerList.clear();

                // SHOW LIST RESTAURANT
                showRestaurantList();
            }
        });
    }


    // METHOD FOR GET IMPLEMENT RECYCLER VIEW
    private void showRestaurantList(){

        // DECLARE AND IMPLEMENT COUNT
        final int[] count = {0};

        // SECURITY DATABASE
        if (FirebaseAuth.getInstance().getCurrentUser() != null){

            // GET USERS COLLECTION FROM CLOUD
            db.collection("restaurant")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){
                        @Override
                        public void onComplete(@NonNull final Task<QuerySnapshot> task) {
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


                                    // NUMBER WORKMATES PER RESTAURANT
                                    try {
                                        // GET NUMBER WORMATES PER RESTAURANT
                                        number = objectRestaurant.getWormates();

                                    }catch (Exception e){
                                        number = 0;
                                    }


                                    // ADD DATA INTO OBJECT LIST
                                    restaurantObjectRecyclerList.add(new RestaurantObjectRecycler(
                                            objectRestaurant.getNameRestaurant(),
                                            street,
                                            village,
                                            objectRestaurant.getTime_close(),
                                            "",
                                            objectRestaurant.getRating(),
                                            objectRestaurant.getWorkmates(),
                                            objectRestaurant.getDistance(),
                                            objectRestaurant.getUrl_photo(),
                                            objectRestaurant.getId()));

                                    Log.e("--workmates--","-- number -- " + objectRestaurant.getWormates());


/*
                                    // SORT NAME RESTAURANT
                                    Collections.sort(restaurantObjectRecyclerList, new Comparator<RestaurantObjectRecycler>() {

                                        @Override
                                        public int compareTo(RestaurantObjectRecycler restaurantObjectRecycler, RestaurantObjectRecycler t1) {

                                            // COMPARE TO DISTANCE
                                            return compareTo(restaurantObjectRecycler.getName(),t1.getName());
                                        }
                                    });

*/

                                    // IMPLEMENT RECYCLER VIEW
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                    recyclerView.setAdapter(new AdapterListView(restaurantObjectRecyclerList));
                                }
                            }
                        }
                    });

        }


    }

    // MEHTOD CLICK ON RECYCLER VIEW
    private void configureOnClickRecyclerView() {
        ItemClickSupport.addTo(recyclerView, R.layout.fragment_list_view)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                        // DECLARE SHARED
                        preferences = getActivity().getSharedPreferences(MYSHARED, Context.MODE_PRIVATE);

                        // SAVE ID RESTAURANT INTO SHARED
                        preferences.edit().putString(ID_RESTAURANT, restaurantObjectRecyclerList.get(position).getId()).commit();

                        // ON CLICK INTENT
                        startActivity(new Intent(getContext(), ActivityShowRestaurant.class));

                    }

                });

    }


}

































