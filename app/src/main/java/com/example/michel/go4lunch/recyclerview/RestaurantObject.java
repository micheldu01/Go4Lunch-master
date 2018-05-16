package com.example.michel.go4lunch.recyclerview;


import android.support.annotation.NonNull;

public class RestaurantObject implements Comparable<RestaurantObject> {


    // CREATE VALUE
    private String name;

    private String address;

    private String heuresOuverture;

    private String type;

    private int etoile;

    private int workMates;

    private int distance;

    private String urlPhoto;


    // CONSTRUCTOR EMPTY
    public RestaurantObject(){
    }

    // --- T E S T ----


    public RestaurantObject(String name, String type, String address, String heuresOuverture, int distance) {
        this.name = name;
        this.type = type;
        this.address = address;
        this.heuresOuverture = heuresOuverture;
        this.distance = distance;
    }




    //--------------------------------------------------



    // CONSTRUCTOR FULL WITH PIC
    public RestaurantObject(String name, String address, String heuresOuverture, String type, int etoile, int workMates, int distance, String urlPhoto) {
        this.name = name;
        address = address;
        this.heuresOuverture = heuresOuverture;
        this.type = type;
        this.etoile = etoile;
        this.workMates = workMates;
        distance = distance;
        this.urlPhoto = urlPhoto;
    }

    // CONSTRUCTOR WITHOUT PIC


    public RestaurantObject(String name, String address, String heuresOuverture, String type, int etoile, int workMates, int distance) {
        this.name = name;
        address = address;
        this.heuresOuverture = heuresOuverture;
        this.type = type;
        this.etoile = etoile;
        this.workMates = workMates;
        distance = distance;
    }



    // GETTER AND SETTER
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return address;
    }

    public void setAddress(String adresse) {
        adresse = adresse;
    }

    public String getHeuresOuverture() {
        return heuresOuverture;
    }

    public void setHeuresOuverture(String heuresOuverture) {
        this.heuresOuverture = heuresOuverture;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEtoile() {
        return etoile;
    }

    public void setEtoile(int etoile) {
        this.etoile = etoile;
    }

    public int getWorkMates() {
        return workMates;
    }

    public void setWorkMates(int workMates) {
        this.workMates = workMates;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        distance = distance;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }



    // ADD METHOD FOR SORT DISTANCE
    @Override
    public int compareTo(@NonNull RestaurantObject otherRestaurant) {
        // INTEGER ARGUMENT FOR COMPARE
        return Integer.compare(distance,  otherRestaurant.getDistance());
    }
}






































