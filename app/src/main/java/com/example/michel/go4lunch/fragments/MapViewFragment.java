package com.example.michel.go4lunch.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michel.go4lunch.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapViewFragment extends FragmentActivity implements OnMapReadyCallback{



    // MAPS POSITION
    private static final LatLng PARIS = new LatLng(48.858093, 2.294694);


/*
    //create constructor
    public static MapViewFragment newInstance() {
        // Required empty public constructor
        return (new MapViewFragment());
    }
   */


    public MapViewFragment newInstance(){
        return (new MapViewFragment());
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout for this fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);



        mapFragment.getMapAsync(this);



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        //ajoute un marker sur Paris
        googleMap.addMarker(new MarkerOptions().title("Paris").position(PARIS));

        //centre la google map sur Paris (avec animation de zoom)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PARIS, 15));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);


    }
}





































