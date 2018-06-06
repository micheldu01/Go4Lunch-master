package com.example.michel.go4lunch.fragments;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.michel.go4lunch.APIMaps.MapStreams;
import com.example.michel.go4lunch.ActivityShowRestaurant;
import com.example.michel.go4lunch.models.ObjectRestaurant;
import com.example.michel.go4lunch.APIMaps.apiNearby.GoogleApiA;
import com.example.michel.go4lunch.BuildConfig;
import com.example.michel.go4lunch.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import static com.example.michel.go4lunch.shared.Shared.MYSHARED;
import static com.example.michel.go4lunch.shared.Shared.NAME_RESTAURANT;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapViewFragment extends Fragment implements OnMapReadyCallback,
            GoogleApiClient.ConnectionCallbacks,
            GoogleApiClient.OnConnectionFailedListener,
        LocationListener {


    // DECLARE SUPPORT MAP FRAGMENT
    private SupportMapFragment mapFragment;

    // DECLARE GOOGLE MAP
    private GoogleMap mMap;

    // DECLARE GOOGLE API CLIENT
    private GoogleApiClient client;

    // DECLARE LOCATION REQUEST
    private LocationRequest locationRequest;

    // DECLARE LOCATION (LAST)
    private Location lastLocation;

    // DECLARE MARKER
    private Marker currentLocationMarker;

    // DECLARE INT
    public static final int REQUEST_LOCATION_CODE = 99;

    // DECLARE PROXIMITY RADIUS
    int PROXIMITY_RADIUS = 500;

    // DECLARE LATITUDE LONGITUDE
    double latitude, longitude;

    // DECLARE DISPOSABLE
    private Disposable disposable;

    // DECLARE ARRAY OBJECT RESTAURANT
    private List<ObjectRestaurant> objectRestaurantList = new ArrayList<>();

    // DECLARE PLACE ID
    private String idPlaceAPI;

    // DECLARE INT i FOR WHILE
    int i = 0;
    int num = 0;

    // DECLARE DATA BASE
    FirebaseFirestore db;

    // SHARED PREFERENCES
    private SharedPreferences preferences;







    public MapViewFragment() {
    }


    public static MapViewFragment newInstance() {
        // Required empty public constructor
        return (new MapViewFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_map_view, container, false);

        // IF THE VERSION IS DIFFERENT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // CALL METHOD CHECK LOCATION
            checkLocationPermission();
        }

        // IMPLEMENT SUPPORT MAP FRAGMENT
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        // IF MAP == NULL
        if (mapFragment == null) {

            // DECLARE FRAGMENT MANAGER
            FragmentManager fm = getFragmentManager();

            // IMPLEMENT FRAGMENT MANAGER INTO FRAGMENT TRANSACTION
            FragmentTransaction ft = fm.beginTransaction();

            // IMPLEMENT SUPPORT MAP FRAGMENT
            mapFragment = SupportMapFragment.newInstance();
            ft.replace(R.id.map, mapFragment).commit();
        }

        // GET MAPS ASYNC
        mapFragment.getMapAsync(this);

        // GET DATA FROM GOOGLE PLACE
        this.getDataFromGooglePlace();

        // SHOW RESTAURANT ON MAP
        this.showRestaurantOnMap();

        return v;

    }

    // METHOD FOR PERMISSION RESULT


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){

            case REQUEST_LOCATION_CODE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                    // PERMISSION IF GRANTED
                    if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED){
                        if(client == null){
                            // CREATE GOOGLE API CLIENT
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else {
                    // PERMISSION IS DENIED
                    Toast.makeText(getContext(),"Permission denied", Toast.LENGTH_LONG).show();
                }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // DECLARE GOOGLE MAP
        mMap = googleMap;
        // SET MAP TYPE NORMAL
        mMap.setMapType(googleMap.MAP_TYPE_NORMAL);


        // IMPLEMENT GOOGLE MAP WHITE ENABLE POSITION
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {

            // BUILD API CLIENT
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);

            // DECLARE INT
            num = 0;

            // MAKE WHILE FOR IMPLEMENT MARKER OPTION
            while (num<=objectRestaurantList.size()-1) {

                // IMPLEMENT GOOGLE MAP WITH MARKER OPTION
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(objectRestaurantList.get(num).getLatitude(),objectRestaurantList.get(num).getLongitude()))
                        .title(objectRestaurantList.get(num).getNameRestaurant())
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_restaurant_orange2)));

                // IMPLEMENT ON CLICK MARKER
                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {

                        // IMPLEMENT SHARED PREFERENCES
                        preferences = getActivity().getSharedPreferences(MYSHARED, Context.MODE_PRIVATE);

                        // PUT NAME RESTAURANT INTO SHARED
                        preferences.edit().putString(NAME_RESTAURANT, marker.getTitle()).commit();

                        // ADD INTENT FO GO TO RESTAURANT
                        startActivity(new Intent(getActivity(), ActivityShowRestaurant.class));

                        return false;
                    }
                });

                // INCREMENT INT WILE
                num++;
            }
        }
    }

    // METHOD GOOGLE API CLIENT
    protected synchronized void buildGoogleApiClient(){

        client = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        client.connect();
    }


    // IMPLEMENT LAST LOCATION
    @Override
    public void onLocationChanged(Location location) {

        // IMPLEMENT LOCATION
        lastLocation = location;
        // IMPLEMENT LATITUDE
        latitude = location.getLatitude();
        // IMPLEMENT LONGITUDE
        longitude = location.getLongitude();



        // REMOVE MARKER CURRENT LOCATION IF DIFFERENT OF NULL
        if(currentLocationMarker != null){
            currentLocationMarker.remove();
        }
        // IMPLEMENT LATITUDE LONGITUDE
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());


        // DECLARE MARKER OPTION
        MarkerOptions markerOptions = new MarkerOptions();

        // IMPLEMENT MARKER OPTION WITH POSITION
        markerOptions.position(latLng);
        // IMPLEMENT TITLE
        markerOptions.title("Current location");
        // ADD ICON AND BLUE COLOR
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

        // IMPLEMENT CURRENT LOCATION WITH MARKER OPTION
        currentLocationMarker = mMap.addMarker(markerOptions);

        // ADD MOVE AND ZOOM
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(7));

        // IF GOOGLE API CLIENT IS NOT NULL
        if(client != null){

            // REMOVE LOCATION UPDATE
            //LocationServices.FusedLocationApi.removeLocationUpdates(client, this);
            LocationServices.getFusedLocationProviderClient(getActivity());
        }
    }

    // IMPLEMENT LOCATION REQUEST
    @Override
    public void onConnected(@Nullable Bundle bundle) {

        // IMPLEMENT LOCATION REQUEST
        locationRequest = new LocationRequest();

        // ADD TIME REQUEST
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        // ADD PRIORITY
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);


        // ASK IF CONTEXT COMPAT HAVE ACCESS
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            // IMPLEMENT LOCATION SERVICE
            //LocationServices.FusedLocationApi.requestLocationUpdates(client,locationRequest,this);
            LocationServices.getFusedLocationProviderClient(getActivity());
        }
    }


    // METHOD ASK CHECK PERMISSION LOCATION
    public boolean checkLocationPermission(){

        // ASK IF PERMISSION
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){

            if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)){

                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_LOCATION_CODE);
            }else{
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_LOCATION_CODE);
            }
            return false;
        }
        return false;
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    // METHOD FOR GET DATA FROM STREAMS
    // AND PUT DATA INTO FIRE BASE
    private void getDataFromGooglePlace(){

        // GET CALENDAR
        Calendar calendar = Calendar.getInstance();
        // GET NUMBER OF DAY OF WEEK
        final int day = calendar.get(Calendar.DAY_OF_WEEK);


        // IMPLEMENT DISPOSABLE WITH GoogleApiA
        disposable = MapStreams.streamGoogleApi(BuildConfig.KEY_GOOGLE_MAP)
                .subscribeWith(new DisposableObserver<GoogleApiA>() {

                    // GET DATA FROM STREAM IN ON NEXT METHOD
                    @Override
                    public void onNext(final GoogleApiA googleAPI) {

                        // DECLARE AND IMPLEMENT FIRE BASE DATA BASE
                        db = FirebaseFirestore.getInstance();


                        // DECLARE VALUE INT
                        i = 0;
                        // CREATE WHILE FOR PUT DATA INTO OBJECT RESTAURANT
                        while (i <= googleAPI.getResults().size()-1){

                        // GET KEY PLACE FOR USE GoogleAPIplaceId
                        idPlaceAPI = googleAPI.getResults().get(i).getPlace_id();

                            // PUT DATA INTO RESTAURANT OBJECT LIST
                            objectRestaurantList.add(new ObjectRestaurant(
                                    googleAPI.getResults().get(i).getName(),
                                    googleAPI.getResults().get(i).getId(),
                                    googleAPI.getResults().get(i).getVicinity(),
                                    googleAPI.getResults().get(i).getGeometry().getLocation().getLatitude(),
                                    googleAPI.getResults().get(i).getGeometry().getLocation().getLongitude(),
                                    googleAPI.getResults().get(i).getPlace_id()
                            ));

                            // PUT RESTAURANT OBJECT INTO DATA BASE FIRE FORE
                            db.collection("restaurant").document(objectRestaurantList.get(i).getId()).set(objectRestaurantList.get(i), SetOptions.merge());

                            // INCREMENT I
                            i++;
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG","On Error"+Log.getStackTraceString(e));
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

    //SHOW RESTAURANT ON MAP
    private void showRestaurantOnMap(){





    }

}

















































