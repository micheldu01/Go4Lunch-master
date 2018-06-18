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


    // CONSTRUCTOR EMPTY
    public User() {}


    // CONSTRUCTOR
    public User(String uid, String username, String urlPicture, String choice) {
        this.uid = uid;
        this.username = username;
        this.urlPicture = urlPicture;
        this.choice = choice;
    }


    // CONSTRUCTOR WITHOUT UID
    public User(String choice, String username, String urlPicture) {
        this.choice = choice;
        this.username = username;
        this.urlPicture = urlPicture;
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
}
