package com.example.michel.go4lunch.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michel.go4lunch.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapViewFragment extends Fragment implements OnMapReadyCallback {


    // DECLARE SUPPORT MAP FRAGMENT
    private SupportMapFragment mapFragment;


    public MapViewFragment(){}


    public static MapViewFragment newInstance() {
        // Required empty public constructor
        return (new MapViewFragment());
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_map_view, container, false);


        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        if(mapFragment == null) {

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            ft.replace(R.id.map, mapFragment).commit();
        }

        mapFragment.getMapAsync(this);




    return v;

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
