package com.example.hotelreviewsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hotelreviewsystem.database.Database;

public class AddHotel extends AppCompatActivity {
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_hotel);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
        database = new Database(this);

        EditText inputHotelName = findViewById(R.id.inputHotelName);
        EditText inputHotelCity = findViewById(R.id.inputHotelCity);
        EditText inputHotelLocation = findViewById(R.id.inputHotelLocation);
        Button saveHotelButton = findViewById(R.id.saveHotelButton);

        saveHotelButton.setOnClickListener(view -> {
            String hotelName = inputHotelName.getText().toString().trim();
            String hotelCity = inputHotelCity.getText().toString().trim();
            String hotelLocation = inputHotelLocation.getText().toString().trim();

            Boolean result=database.insertHotel(hotelName,hotelCity,hotelLocation);
            if(result){
                Toast.makeText(this,"Hotel added successfully",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "System Error", Toast.LENGTH_SHORT).show();
            }
            finish();
        });
    }
}