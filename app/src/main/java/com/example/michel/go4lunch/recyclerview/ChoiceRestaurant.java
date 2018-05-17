package com.example.michel.go4lunch.recyclerview;


import android.widget.ImageView;

public class ChoiceRestaurant {

    // NAME PROFILE
    private String name;
    private String urlImage;

    // CONSTRUCTOR EMPTY
    public ChoiceRestaurant() {}

    // CONSTRUCTOR FULL


    public ChoiceRestaurant(String name, String urlImage) {
        this.name = name;
        this.urlImage = urlImage;
    }


    // GETTER ET SETTER
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
