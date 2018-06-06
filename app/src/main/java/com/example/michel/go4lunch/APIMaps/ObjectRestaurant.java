package com.example.michel.go4lunch.APIMaps;


import android.support.annotation.Nullable;

// CREATE OBJECT RESTAURANT
public class ObjectRestaurant {


    // DECLARE STRING NAME RESTAURANT
    private String nameRestaurant;

    // DECLARE STRING ID
    private String id;

    // DECLARE STRING  ADDRESS
    private String address;

    // DECLARE STRING TIME OPEN
    @Nullable
    private boolean time_open;

    // DECLARE DOUBLE LATITUDE
    private Float latitude;

    // DECLARE DOUBLE LONGITUDE
    private Float longitude;


    // DECLARE STRING PLACE ID
    private String place_id;

    // DECLARE STRING SITE WEB
    @Nullable
    private String web;

    // DECLARE INT PHONE
    @Nullable
    private String phone;

    // DECLARE NUMBER STREET ADDRESS
    @Nullable
    private String number_street;

    // DECLARE NAME STREET
    @Nullable
    private String name_street;

    // DECLARE TIME CLOSE
    @Nullable
    private String time_close;

    // DECLARE PHOTO KEY
    @Nullable
    private String photo_key;

    // DECLARE URL PHOTO
    @Nullable
    private String url_photo;


    // CONSTRUCTOR WITHOUT TIME CLOSE AND TIME
    public ObjectRestaurant(String nameRestaurant, String id, String address, Float latitude, Float longitude, String place_id) {
        this.nameRestaurant = nameRestaurant;
        this.id = id;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.place_id = place_id;
    }



    // GETTER AND SETTER

    public String getNameRestaurant() {
        return nameRestaurant;
    }

    public void setNameRestaurant(String nameRestaurant) {
        this.nameRestaurant = nameRestaurant;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Nullable
    public boolean isTime_open() {
        return time_open;
    }

    public void setTime_open(@Nullable boolean time_open) {
        this.time_open = time_open;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    @Nullable
    public String getWeb() {
        return web;
    }

    public void setWeb(@Nullable String web) {
        this.web = web;
    }

    @Nullable
    public String getPhone() {
        return phone;
    }

    public void setPhone(@Nullable String phone) {
        this.phone = phone;
    }

    @Nullable
    public String getNumber_street() {
        return number_street;
    }

    public void setNumber_street(@Nullable String number_street) {
        this.number_street = number_street;
    }

    @Nullable
    public String getName_street() {
        return name_street;
    }

    public void setName_street(@Nullable String name_street) {
        this.name_street = name_street;
    }

    @Nullable
    public String getTime_close() {
        return time_close;
    }

    public void setTime_close(@Nullable String time_close) {
        this.time_close = time_close;
    }

    @Nullable
    public String getPhoto_key() {
        return photo_key;
    }

    public void setPhoto_key(@Nullable String photo_key) {
        this.photo_key = photo_key;
    }

    @Nullable
    public String getUrl_photo() {
        return url_photo;
    }

    public void setUrl_photo(@Nullable String url_photo) {
        this.url_photo = url_photo;
    }
}
