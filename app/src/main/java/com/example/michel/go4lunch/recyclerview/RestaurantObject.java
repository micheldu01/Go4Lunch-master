package com.example.michel.go4lunch.recyclerview;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class RestaurantObject implements Comparable<RestaurantObject> {


    // CREATE VALUE
    private String name;

    private String address;

    private String heuresOuverture;

    private String type;

    private int star;

    private int workMates;

    private int distance;

    private String urlPhoto;


    // CONSTRUCTOR EMPTY
    public RestaurantObject(){
    }



    // CONSTRUCTOR FULL VALUE
    public RestaurantObject(String name, String type, String address, String heuresOuverture, int distance, int workMates, int star, String urlPhoto) {
        this.name = name;
        this.type = type;
        this.address = address;
        this.heuresOuverture = heuresOuverture;
        this.distance = distance;
        this.workMates = workMates;
        this.star = star;
        this.urlPhoto = urlPhoto;
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

    public int getStar() {
        return star;
    }

    public void setStar(int star) { this.star = star;
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






































