package com.example.hotelreviewsystem;

public class Hotel {
    private final String id;
    private final String name;
    private final String city;
    private final float rating;

    public Hotel(String id, String name, String city, float rating) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public float getRating() {
        return rating;
    }
}