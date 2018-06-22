package com.example.michel.go4lunch.models;

import android.support.annotation.Nullable;



public class User {

    // DECLARE UID
    private String uid;
    // DECLARE USERNAME
    private String name;
    // DECLARE URL PICTURE
    @Nullable private String photo;
    // DECLARE CHOICE
    @Nullable private String choice;
    // DECLARE NAME RESTAURANT
    private String name_restaurant;


    // CONSTRUCTOR EMPTY
    public User() {}


    // CONSTRUCTOR
    public User(String uid, String name, String photo, String choice, String name_restaurant) {
        this.uid = uid;
        this.name = name;
        this.photo = photo;
        this.choice = choice;
        this.name_restaurant = name_restaurant;
    }

    // CONSTRUCTOR WITHOUT UID
    public User(String choice, String name, String photo, String name_restaurant) {
        this.choice = choice;
        this.name = name;
        this.photo = photo;
        this.name_restaurant = name_restaurant;
    }

    // CONSTRUCTOR FOR ACTIVITY SHOW RESTAURANT
    public User(String name, String photo) {
        this.name = name;
        this.photo = photo;
    }


    // GETTER AN SETTER


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(@Nullable String photo) {
        this.photo = photo;
    }

    @Nullable
    public String getChoice() {
        return choice;
    }

    public void setChoice(@Nullable String choice) {
        this.choice = choice;
    }

    public String getName_restaurant() {
        return name_restaurant;
    }

    public void setName_restaurant(String name_restaurant) {
        this.name_restaurant = name_restaurant;
    }
}





































