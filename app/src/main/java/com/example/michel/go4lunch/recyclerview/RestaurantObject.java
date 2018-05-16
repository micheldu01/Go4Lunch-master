package com.example.michel.go4lunch.recyclerview;



public class RestaurantObject {


    // CREATE VALUE
    private String name;

    private String Address;

    private String heuresOuverture;

    private String type;

    private int etoile;

    private int workMates;

    private int Distance;

    private String urlPhoto;


    // CONSTRUCTOR EMPTY
    public RestaurantObject() {
    }

    // --- T E S T --
    public RestaurantObject(String name) {
        this.name = name;
    }



    // CONSTRUCTOR FULL WITH PIC
    public RestaurantObject(String name, String address, String heuresOuverture, String type, int etoile, int workMates, int distance, String urlPhoto) {
        this.name = name;
        Address = address;
        this.heuresOuverture = heuresOuverture;
        this.type = type;
        this.etoile = etoile;
        this.workMates = workMates;
        Distance = distance;
        this.urlPhoto = urlPhoto;
    }

    // CONSTRUCTOR WITHOUT PIC


    public RestaurantObject(String name, String address, String heuresOuverture, String type, int etoile, int workMates, int distance) {
        this.name = name;
        Address = address;
        this.heuresOuverture = heuresOuverture;
        this.type = type;
        this.etoile = etoile;
        this.workMates = workMates;
        Distance = distance;
    }



    // GETTER AND SETTER
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return Address;
    }

    public void setAddress(String adresse) {
        Address = adresse;
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
        return Distance;
    }

    public void setDistance(int distance) {
        Distance = distance;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }
}
