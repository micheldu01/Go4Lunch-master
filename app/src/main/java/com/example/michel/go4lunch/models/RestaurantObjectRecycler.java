package com.example.michel.go4lunch.models;


import android.support.annotation.NonNull;

public class RestaurantObjectRecycler implements Comparable<RestaurantObjectRecycler> {


    // CREATE VALUE

    // NAME
    private String name;

    // ADDRESS (STREET)
    private String address;

    // VILLAGE
    private String village;

    // TIME OPEN
    private String heuresOuverture;

    // TYPE FOOD
    private String type;

    // STAR
    private double star;

    // WORKMATES
    private int workmates;

    // DISTANCE
    private int distance;

    // URL PHOTO
    private String urlPhoto;

    // ID RESTAURANT
    private String id;

    //-----------------
    private Float latitude;
    private Float longitude;





    // CONSTRUCTOR EMPTY
    public RestaurantObjectRecycler(){
    }




    // CONSTRUCTOR FULL VALUE


    public RestaurantObjectRecycler(String name, String address, int distance, String id, Float latitude, Float longitude) {
        this.name = name;
        this.address = address;
        this.distance = distance;
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // CONSTRUCTOR FULL VALUE
    public RestaurantObjectRecycler(String name, String address, String village, String heuresOuverture, String type, double star, int workMates, int distance, String urlPhoto, String id) {
        this.name = name;
        this.address = address;
        this.village = village;
        this.heuresOuverture = heuresOuverture;
        this.type = type;
        this.star = star;
        this.workmates = workmates;
        this.distance = distance;
        this.urlPhoto = urlPhoto;
        this.id = id;
    }


    // GETTER AND SETTER


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
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

    public double getStar() { return star; }

    public void setStar(double star) {
        this.star = star;
    }

    public int getWorkMates() {
        return workmates;
    }

    public void setWorkMates(int workMates) {
        this.workmates = workMates;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }



    // ADD METHOD FOR SORT DISTANCE
    @Override
    public int compareTo(@NonNull RestaurantObjectRecycler otherRestaurant) {
        // INTEGER ARGUMENT FOR COMPARE
        return Integer.compare(distance,  otherRestaurant.getDistance());
    }
}






































