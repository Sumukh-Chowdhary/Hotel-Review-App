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

public class Register extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button registerBtn=findViewById(R.id.registerButton);
        EditText usernametext=findViewById(R.id.username_reg);
        EditText passwordtext=findViewById(R.id.password_reg);
        Database db=new Database(this);

        registerBtn.setOnClickListener(view->{
            String username=usernametext.getText().toString();
            String password=passwordtext.getText().toString();

            if(db.checkUser(username)){
                Toast.makeText(this, "User with given username already exists", Toast.LENGTH_SHORT).show();
            }else{
                if(password.length()>=8 && password.length()<=16){
                    Boolean result=db.insertData(username,password);
                    if(result){
                        Toast.makeText(this, "User Registered successfully", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(this,Login.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(this, "User registration failed", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "Password should have a length between 8 and 16 characters", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}