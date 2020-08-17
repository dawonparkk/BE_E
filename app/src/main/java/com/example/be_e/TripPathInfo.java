package com.example.be_e;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TripPathInfo {
    String name, theme;
    double latitude, longtitude;

//    String trip1, trip2, trip3;
//    double la1, lo1, la2, lo2, la3, lo3;

    ArrayList<String> trips;
    ArrayList<Double> locations;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public ArrayList<String> getTrips() {
        return trips;
    }

    public void setTrips(ArrayList<String> trips) {
        this.trips = trips;
    }

    public ArrayList<Double> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Double> locations) {
        this.locations = locations;
    }
}
