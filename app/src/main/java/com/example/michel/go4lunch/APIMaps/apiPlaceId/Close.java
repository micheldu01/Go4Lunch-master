package com.example.michel.go4lunch.APIMaps.apiPlaceId;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Close {


    @SerializedName("day")
    @Expose
    public int day;

    @SerializedName("time")
    @Expose
    public String time;


    public Close(String time) {
        this.time = time;
    }

    public Close(int day, String time) {
        this.day = day;
        this.time = time;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
