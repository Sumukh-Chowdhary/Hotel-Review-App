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

public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText usernameBar=findViewById(R.id.username);
        EditText passwordBar=findViewById(R.id.password);
        Button loginbtn=findViewById(R.id.loginButton);
        Button register=findViewById(R.id.goRegister);
        Database db=new Database(this);

        loginbtn.setOnClickListener(view->{
            String username=usernameBar.getText().toString();
            String password=passwordBar.getText().toString();

            Boolean userExist=db.checkUser(username);
            if(userExist){
                if(db.loginCheck(username,password)){
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this,UserHome.class));
                }else{
                    Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "User with given username does not exist", Toast.LENGTH_SHORT).show();
            }
            if(username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Both username and password are required", Toast.LENGTH_SHORT).show();
                return;
            }
            if(username.equals("hotelAdmin") && password.equals("admin")){
                Toast.makeText(this, "Login Successful into Admin DashBoard", Toast.LENGTH_SHORT).show();
                Intent adminHome=new Intent(this,AdminHome.class);
                startActivity(adminHome);
            }else{
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                return;
            }
        });

        register.setOnClickListener(view->{
            Intent intent=new Intent(this, Register.class);
            startActivity(intent);
        });
    }
}