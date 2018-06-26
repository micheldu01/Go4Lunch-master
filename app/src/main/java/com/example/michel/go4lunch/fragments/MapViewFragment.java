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
import com.example.michel.go4lunch.APIMaps.apiPlaceId.GoogleAPIplaceId;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import static com.example.michel.go4lunch.shared.Shared.ID_RESTAURANT;
import static com.example.michel.go4lunch.shared.Shared.MYSHARED;


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
    FirebaseFirestore db= FirebaseFirestore.getInstance();

    // SHARED PREFERENCES
    private SharedPreferences preferences;

    // DECLARE DAY
    private int day_count = 0;

    // DECLARE URL RESTAURANT
    private String url_photo;

    // DECLARE TIME
    private String time;

    // DECLARE IS OPEN
    private boolean is_open;

    // DECLARE RATING
    private double rating;

    // DECLARE DISTANCE
    private int distance;














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

        // IMPLEMENT SHARED PREFERENCES
        this.implementShared();

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

    // CONSTRUCTOR MAP SHOW
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // DECLARE GOOGLE MAP
        mMap = googleMap;
        // SET MAP TYPE NORMAL
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // SET ZOOM MAP
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(46,5.3),11));


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

                // IMPLEMENT LIST ID RESTAURANT CHOICE WITH GREEN ICON
                askIfRestaurantChoice(objectRestaurantList.get(num).getId(),objectRestaurantList.get(num).getLatitude(),objectRestaurantList.get(num).getLongitude());

                    // IMPLEMENT GOOGLE MAP WITH MARKER OPTION ORANGE
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(objectRestaurantList.get(num).getLatitude(),objectRestaurantList.get(num).getLongitude()))
                            .snippet(objectRestaurantList.get(num).getId())
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_restaurant_orange2)));


                // IMPLEMENT ON CLICK MARKER
                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {

                        // HIDE INFO WINDOW MARKER
                        marker.hideInfoWindow();

                        // PUT NAME RESTAURANT INTO SHARED
                        preferences.edit().putString(ID_RESTAURANT, marker.getSnippet()).commit();

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
    public void onConnected(@Nullable Bundle bundle) { }


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

        // IMPLEMENT DISPOSABLE WITH GoogleApiA
        disposable = MapStreams.streamGoogleApi(BuildConfig.KEY_GOOGLE_MAP)
                .subscribeWith(new DisposableObserver<GoogleApiA>() {


                    // GET DATA FROM STREAM IN ON NEXT METHOD
                    @Override
                    public void onNext(final GoogleApiA googleAPI) {



                            // CREATE WHILE FOR PUT DATA INTO OBJECT RESTAURANT
                            //while (i <= googleAPI.getResults().size() - 1) {
                            for(i = 0; i < googleAPI.getResults().size(); i++){


                                // GET KEY PLACE FOR USE GoogleAPIplaceId
                                idPlaceAPI = googleAPI.getResults().get(i).getPlace_id();


                                // GET RATING STAR
                                try{
                                    // IMPLEMENT RATING
                                    rating = googleAPI.getResults().get(i).getRating()-2;

                                }catch (Exception e){
                                    // IMPLEMENT 0 IF RATING NOT EXIST
                                    rating = 0;
                                }


                                // GET DISTANCE BETWEEN 2 LOCATIONS
                                float dist[] = new float[10];
                                Location.distanceBetween(46.05, 5.3333, googleAPI.getResults().get(i).getGeometry().getLocation().getLatitude(), googleAPI.getResults().get(i).getGeometry().getLocation().getLongitude(), dist);

                                // TURN MILES INTO METER
                                turnMilesIntoMeter(dist[0]);


                                // GET DATA RESTAURANT FROM GOOGLE API

                                // DECLARE DISPOSABLE WITH STREAM GOOGLE API PLACE ID
                                Disposable disposable = MapStreams.streamGoogleAPIplaceId(BuildConfig.KEY_GOOGLE_MAP, idPlaceAPI)
                                        .subscribeWith(new DisposableObserver<GoogleAPIplaceId>() {
                                            @Override
                                            public void onNext(GoogleAPIplaceId googleAPIplaceId) {


                                                // GET PHOTO RESTAURANT
                                                try {
                                                    // IF PHOTO IS NOT NULL GET PHOTO
                                                        url_photo = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=800&photoreference="
                                                                + googleAPIplaceId.getResultsAPI().getPhotos().get(0).getPhotoReference() + "&key=" + BuildConfig.KEY_GOOGLE_MAP_1;

                                                } catch (Exception e) {
                                                    // IMPLEMENT URL RESTAURANT
                                                    url_photo = null;
                                                }


                                                // GET CURRENT DAY
                                                Calendar c = Calendar.getInstance();

                                                // IMPLEMENT DAY
                                                day_count = c.get(Calendar.DAY_OF_WEEK);


                                                // MAKE SAME CURRENT DAY WITH GOOGLE API
                                                if (day_count < 2) {
                                                    day_count = day_count + 5;
                                                } else {
                                                    day_count = day_count - 2;
                                                }

                                                // USE TRY CATCH FOR GET CLOSE TIME RESTAURANT
                                                try {

                                                    // IMPLEMENT TIME CLOSE RESTAURANT
                                                    time = googleAPIplaceId.getResultsAPI().getOpening_hours().getPeriods().get(day_count).getClose().getTime();


                                                } catch (Exception e) {

                                                    // IF TIME CLOSE DO NOT EXIST IMPLEMENT TIME
                                                    time = "----";
                                                }


                                                // USE TRY CATCH FOR KNOW IF RESTAURANT IS OPEN NOW
                                                try {

                                                    // ASK IF RESTAURANT IS OPEN NOW AND IMPLEMENT ANSWER
                                                    is_open = googleAPIplaceId.getResultsAPI().getOpening_hours().isOpen_now();



                                                    // IF RESTAURANT IS CLOSE

                                                    if (is_open == false) {

                                                        // IMPLEMENT TIME
                                                        time = "close";
                                                    }
                                                } catch (Exception e) {

                                                    time = "---";
                                                }

                                                // ADD URL AND TIME INTO DATABASE


                                                // SECURITY DATABASE
                                                if (FirebaseAuth.getInstance().getCurrentUser() != null){

                                                    // IMPLEMENT DATABASE
                                                    CollectionReference restaurant = db.collection("restaurant");

                                                    Map<String, Object> data = new HashMap<>();
                                                    data.put("url_photo",url_photo);
                                                    data.put("time_close",time);
                                                    //data.put("open_now",googleAPIplaceId.getResultsAPI().getOpening_hours().isOpen_now());
                                                    restaurant.document(googleAPIplaceId.getResultsAPI().getId()).set(data, SetOptions.merge());
                                                }


                                            }
                                            @Override
                                            public void onError(Throwable e) {
                                            }

                                            @Override
                                            public void onComplete() {
                                            }
                                        });



                                // SECURITY DATABASE
                                if (FirebaseAuth.getInstance().getCurrentUser() != null){

                                    // IMPLEMENT DATABASE
                                    CollectionReference restaurant = db.collection("restaurant");

                                    // USE HASH MAP FOR PUT DATA
                                    Map<String, Object> data = new HashMap<>();

                                    // NAME RESTAURANT
                                    data.put("nameRestaurant",googleAPI.getResults().get(i).getName());

                                    // ID
                                    data.put("id",googleAPI.getResults().get(i).getId());

                                    // ADDRESS
                                    data.put("address",googleAPI.getResults().get(i).getVicinity());

                                    // LATITUDE
                                    data.put("latitude",googleAPI.getResults().get(i).getGeometry().getLocation().getLatitude());

                                    // LONGITUDE
                                    data.put("longitude",googleAPI.getResults().get(i).getGeometry().getLocation().getLongitude());

                                    // PLACE ID
                                    data.put("place_id",googleAPI.getResults().get(i).getPlace_id());

                                    // RATING
                                    data.put("rating",rating);

                                    // DISTANCE
                                    data.put("distance",distance);

                                    // PUT DATA IN DATABASE
                                    restaurant.document(googleAPI.getResults().get(i).getId()).set(data, SetOptions.merge());
                                }


                                // PUT DATA INTO RESTAURANT OBJECT LIST
                                objectRestaurantList.add(new ObjectRestaurant(
                                        googleAPI.getResults().get(i).getId(),
                                        googleAPI.getResults().get(i).getGeometry().getLocation().getLatitude(),
                                        googleAPI.getResults().get(i).getGeometry().getLocation().getLongitude()
                                ));


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


    // METHOD TO ASK IF RESTAURANT IS CHOICE
    private void askIfRestaurantChoice(final String id_restaurant, final Float latitude, final Float longitude){


        // SECURITY DATABASE
        if (FirebaseAuth.getInstance().getCurrentUser() != null){

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

                                        // IMPLEMENT GOOGLE MAP WITH MARKER OPTION ORANGE
                                        mMap.addMarker(new MarkerOptions()
                                                .position(new LatLng(latitude,longitude))
                                                .snippet(id_restaurant)
                                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_restaurant_vert2)));
                                    }
                                }
                            }
                        }
                    });
        }


    }

    // IMPLEMENT SHARED PREFERENCES
    private void implementShared(){

        // IMPLEMENT SHARED PREFERENCES
        preferences = getActivity().getSharedPreferences(MYSHARED, Context.MODE_PRIVATE);
    }

    // METHOD TURN MILES INTO METER
    private void turnMilesIntoMeter(float miles){

        // GET MILES
        distance = (int)Math.round(miles);
    }

}



















































