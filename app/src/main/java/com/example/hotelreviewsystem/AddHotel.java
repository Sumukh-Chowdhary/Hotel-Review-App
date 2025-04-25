package com.example.hotelreviewsystem;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_hotel);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText hotel=findViewById(R.id.hotelName);
        EditText cityObj=findViewById(R.id.cityName);
        EditText locationObj=findViewById(R.id.location);
        Button submit=findViewById(R.id.saveHotel);
        Database database=new Database(this);

        submit.setOnClickListener(view->{
            String name=hotel.getText().toString();
            String city=cityObj.getText().toString();
            String location=locationObj.getText().toString();

            Boolean result=database.insertHotel(name,city,location);
            if(result){
                Toast.makeText(this, "Hotel added successfully", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Error occured", Toast.LENGTH_SHORT).show();
            }
            finish();
        });
    }
}