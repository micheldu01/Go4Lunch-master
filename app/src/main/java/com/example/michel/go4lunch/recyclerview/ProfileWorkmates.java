package com.example.michel.go4lunch.recyclerview;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.michel.go4lunch.base.BaseActivity;

public class ProfileWorkmates extends BaseActivity implements Comparable<ProfileWorkmates> {


    // NAME PROFILE
    private String name;
    // URL IMAGE
    private String urlImage;
    // NAME RESTAURANT
    private String nameRestaurant;
    // BOOLEAN CHOICE RESTAURANT
    private boolean choice;


    // EMPTY CONSTRUCTOR
    public ProfileWorkmates() {}


    // CONSTRUCTOR FULL VALUES
    public ProfileWorkmates(boolean choice, String name, String urlImage,@Nullable String nameRestaurant) {
        this.choice = choice;
        this.name = name;
        this.urlImage = urlImage;
        this.nameRestaurant = nameRestaurant;
    }
/*
    // CONSTRUCTOR WITHOUT RESTAURANT AND STYLE
    public ProfileWorkmates(boolean choice, String name, String urlImage) {
        this.choice = choice;
        this.name = name;
        this.urlImage = urlImage;
    }
*/


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

    public String getNameRestaurant() {
        return nameRestaurant;
    }

    public void setNameRestaurant(String nameRestaurant) {
        this.nameRestaurant = nameRestaurant;
    }

    public boolean isChoice() {
        return choice;
    }

    public void setChoice(boolean choice) {
        this.choice = choice;
    }

    // METHOD FOR SORT TYPE FOOD
    @Override
    public int compareTo(ProfileWorkmates profileWorkmates) {


            return profileWorkmates.getNameRestaurant().compareTo(nameRestaurant);


    }
}
