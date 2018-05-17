package com.example.michel.go4lunch.auth;


import com.example.michel.go4lunch.base.BaseActivity;

public class ProfileActivity extends BaseActivity {


    // NAME PROFILE
    private String name;

    // URL IMAGE
    private String urlImage;


    // CONSTRUCTOR EMPTY
    public ProfileActivity() {}


    // CONSTRUCTOR FOR WORKMATES RECYCLER
    public ProfileActivity(String name, String urlImage) {
        this.name = name;
        this.urlImage = urlImage;
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
}
