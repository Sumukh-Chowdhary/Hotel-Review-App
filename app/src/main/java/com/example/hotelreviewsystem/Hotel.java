package com.example.hotelreviewsystem;

public class Hotel {
    private final String id;
    private final String name;
    private final float rating;

    public Hotel(String id, String name, float rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }
}
