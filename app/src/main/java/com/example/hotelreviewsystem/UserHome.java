package com.example.hotelreviewsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button logout=findViewById(R.id.logout_user);
        Button ratings=findViewById(R.id.view_hotels);
        Button addRating=findViewById(R.id.ratingForm);

        logout.setOnClickListener(v->{
            startActivity(new Intent(this, Login.class));
        });

        ratings.setOnClickListener(v->{
            startActivity(new Intent(this,UserHotels.class));
        });

        addRating.setOnClickListener(v->{
            startActivity(new Intent(this,AddRating.class));
        });
    }
}