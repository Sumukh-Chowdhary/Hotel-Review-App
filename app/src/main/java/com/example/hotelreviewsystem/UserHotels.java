package com.example.hotelreviewsystem;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelreviewsystem.database.Database;

import java.util.ArrayList;
import java.util.List;

public class UserHotels extends AppCompatActivity {
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_hotels);

        database = new Database(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewHotels);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Hotel> hotelList = new ArrayList<>();
        Cursor cursor = database.getAllHotels();
        while (cursor.moveToNext()) {
            String hotelId = cursor.getString(cursor.getColumnIndex(Database.COLUMN_HOTEL_ID));
            String hotelName = cursor.getString(cursor.getColumnIndex(Database.COLUMN_HOTEL_NAME));
            String hotelCity = cursor.getString(cursor.getColumnIndex(Database.COLUMN_HOTEL_CITY));
            float hotelRating = cursor.getFloat(cursor.getColumnIndex(Database.COLUMN_RATING));
            hotelList.add(new Hotel(hotelId, hotelName, hotelCity, hotelRating));
        }
        cursor.close();
        HotelAdapter adapter = new HotelAdapter(hotelList);
        recyclerView.setAdapter(adapter);
    }
}