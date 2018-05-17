package com.example.michel.go4lunch.recyclerview;


import com.example.michel.go4lunch.base.BaseActivity;

public class ProfileWorkmates extends BaseActivity {


    // NAME PROFILE
    private String name;
    // URL IMAGE
    private String urlImage;
    // TYPE RESTAURANT
    private String type;
    // NAME RESTAURANT
    private String nameRestaurant;


    // EMPTY CONSTRUCTOR
    public ProfileWorkmates() {}


    // CONSTRUCTOR FULL VALUES


    public ProfileWorkmates(String name, String urlImage, String type, String nameRestaurant) {
        this.name = name;
        this.urlImage = urlImage;
        this.type = type;
        this.nameRestaurant = nameRestaurant;
    }


    // GETTER AND SETTER


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNameRestaurant() {
        return nameRestaurant;
    }

    public void setNameRestaurant(String nameRestaurant) {
        this.nameRestaurant = nameRestaurant;
    }
}
