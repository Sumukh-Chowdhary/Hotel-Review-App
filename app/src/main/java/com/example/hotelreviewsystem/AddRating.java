package com.example.hotelreviewsystem;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelreviewsystem.database.Database;

public class AddRating extends AppCompatActivity {
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rating);

        database = new Database(this);

        EditText inputHotelId = findViewById(R.id.inputHotelId);
        EditText inputRating = findViewById(R.id.inputRating);
        Button submitRatingButton = findViewById(R.id.submitRatingButton);

        submitRatingButton.setOnClickListener(v -> {
            String hotelId = inputHotelId.getText().toString().trim();
            String ratingInput = inputRating.getText().toString().trim();
            if (hotelId.isEmpty() || ratingInput.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            float newRating = Float.parseFloat(ratingInput);
            if (updateHotelRating(hotelId, newRating)) {
                Toast.makeText(this, "Rating updated successfully!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to update rating", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean updateHotelRating(String hotelId, float newRating) {
        float currentRating = database.getHotelRating(hotelId);
        float averageRating = (currentRating + newRating) / 2;
        return database.updateHotelRating(hotelId, averageRating);
    }
}