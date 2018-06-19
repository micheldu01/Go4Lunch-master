package com.example.michel.go4lunch.models;

import android.support.annotation.Nullable;

public class User {

    // DECLARE UID
    private String uid;
    // DECLARE USERNAME
    private String username;
    // DECLARE URL PICTURE
    @Nullable private String urlPicture;
    // DECLARE CHOICE
    @Nullable private String choice;
    // DECLARE NAME RESTAURANT
    private String name_restaurant;


    // CONSTRUCTOR EMPTY
    public User() {}


    // CONSTRUCTOR
    public User(String uid, String username, String urlPicture, String choice, String name_restaurant) {
        this.uid = uid;
        this.username = username;
        this.urlPicture = urlPicture;
        this.choice = choice;
        this.name_restaurant = name_restaurant;
    }

    // CONSTRUCTOR WITHOUT UID
    public User(String choice, String username, String urlPicture, String name_restaurant) {
        this.choice = choice;
        this.username = username;
        this.urlPicture = urlPicture;
        this.name_restaurant = name_restaurant;
    }


    // GETTER AN SETTER

    public String getChoice() {return choice;}

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setChoice(String choice) {this.choice = choice;}

    public void setUsername(String username) {
        this.username = username;
    }

    @Nullable
    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(@Nullable String urlPicture) {
        this.urlPicture = urlPicture;
    }

    public String getName_restaurant() { return name_restaurant; }

    public void setName_restaurant(String name_restaurant) { this.name_restaurant = name_restaurant; }
}
