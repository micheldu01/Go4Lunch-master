package com.example.michel.go4lunch.fragments;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

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
    //private List<ObjectRestaurant> objectRestaurantList = new ArrayList<>();

    // DECLARE PLACE ID
    private String idPlaceAPI;


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
        mMap.setMapType(googleMap.MAP_TYPE_NORMAL);




        // IMPLEMENT GOOGLE MAP WHITE ENABLE POSITION
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {

            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
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
        Log.e("------test--------","-------test---------"+latLng);


        // DECLARE MARKER OPTION
        MarkerOptions markerOptions = new MarkerOptions();

        // IMPLEMENT MARKER OPTION WITH POSITION
        markerOptions.position(latLng);
        // IMPLEMENT TITLE
        markerOptions.title("Current location");
        // ADD ICON AND BLUE COLOR
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

        // LOG
        Log.e("------test--------","-------test---------");

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


    private void testAPI(){

        // get calendar
        Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_WEEK);


        disposable = MapStreams.streamGoogleApi(BuildConfig.KEY_GOOGLE_MAP)
                .subscribeWith(new DisposableObserver<GoogleApiA>() {


                    @Override
                    public void onNext(final GoogleApiA googleAPI) {

                        idPlaceAPI = googleAPI.getResults().get(0).getPlace_id();

                        disposable = MapStreams.streamGoogleAPIplaceId(BuildConfig.KEY_GOOGLE_MAP,idPlaceAPI)
                                .subscribeWith(new DisposableObserver<GoogleAPIplaceId>() {
                                    @Override
                                    public void onNext(GoogleAPIplaceId googleAPIplaceId) {

                                        String str = googleAPIplaceId.getResultsAPI().getWebsite();

                                        Log.e("--test 3--","-- name restaurant"
                                                +str);

                                        objectRestaurantList.add(new ObjectRestaurant(
                                                googleAPI.getResults().get(0).getName(),
                                                googleAPI.getResults().get(0).getId(),
                                                googleAPI.getResults().get(0).getVicinity(),
                                                googleAPI.getResults().get(0).getOpening_hours().isOpen_now(),
                                                googleAPI.getResults().get(0).getGeometry().getLocation().getLatitude(),
                                                googleAPI.getResults().get(0).getGeometry().getLocation().getLongitude(),
                                                googleAPI.getResults().get(0).getPlace_id(),
                                                googleAPIplaceId.getResultsAPI().getWebsite(),
                                                googleAPIplaceId.getResultsAPI().getPhone(),
                                                googleAPIplaceId.getResultsAPI().getAddressComponents().get(0).getShort_name(),
                                                googleAPIplaceId.getResultsAPI().getAddressComponents().get(1).getShort_name(),
                                                googleAPIplaceId.getResultsAPI().getOpening_hours().getPeriods().get(0).getClose().getTime(),
                                                googleAPIplaceId.getResultsAPI().getOpening_hours().getPeriods().get(day-2).getClose().getDay(),
                                                googleAPIplaceId.getResultsAPI().getPhotos().get(0).getPhotoReference(),
                                                "https://maps.googleapis.com/maps/api/place/photo?maxwidth=800&photoreference="+googleAPIplaceId.getResultsAPI().getPhotos().get(0).getPhotoReference()+BuildConfig.KEY_GOOGLE_MAP
                                        ));

                                        Log.e("--API GOOGLE--", "-- name restaurant"
                                                + objectRestaurantList.get(0).getId()
                                                + objectRestaurantList.get(0).getNameRestaurant()
                                                + objectRestaurantList.get(0).getAddress()
                                                + objectRestaurantList.get(0).isTime_open()
                                                + objectRestaurantList.get(0).getLatitude()
                                                + objectRestaurantList.get(0).getLongitude()
                                                + objectRestaurantList.get(0).getPlace_id()
                                                + objectRestaurantList.get(0).getWeb()
                                                + objectRestaurantList.get(0).getPhone()
                                                + objectRestaurantList.get(0).getNumber_street()
                                                + objectRestaurantList.get(0).getName_street()
                                                + objectRestaurantList.get(0).getTime_close()
                                                + objectRestaurantList.get(0).getDays()
                                                + objectRestaurantList.get(0).getPhoto_key()
                                                + objectRestaurantList.get(0).getUrl_photo()

                                        );
                                    }
                                    @Override
                                    public void onError(Throwable e) {
                                    }
                                    @Override
                                    public void onComplete() {
                                    }
                                });
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

}