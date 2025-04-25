package com.example.hotelreviewsystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelreviewsystem.database.Database;

import java.util.ArrayList;
import java.util.List;

public class Hotels extends AppCompatActivity {
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hotels);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button addHotel=findViewById(R.id.addHotel);
        addHotel.setOnClickListener(view->{
            startActivity(new Intent(Hotels.this,AddHotel.class));
        });
        database = new Database(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewHotels);
        refreshHotelList(recyclerView);
    }
    @SuppressLint("Range")
    private void refreshHotelList(RecyclerView recyclerView) {
        List<Hotel> hotelList = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Database.TABLE_HOTEL, null);

        while (cursor.moveToNext()) {
            hotelList.add(new Hotel(
                    cursor.getString(cursor.getColumnIndex(Database.COLUMN_HOTEL_ID)),
                    cursor.getString(cursor.getColumnIndex(Database.COLUMN_HOTEL_NAME)),
                    cursor.getFloat(cursor.getColumnIndex(Database.COLUMN_RATING))
            ));
        }
        cursor.close();
        HotelAdapter adapter = new HotelAdapter(hotelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}