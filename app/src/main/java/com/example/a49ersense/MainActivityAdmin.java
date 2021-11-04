package com.example.a49ersense;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityAdmin extends AppCompatActivity {
    Button getuserdetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        findViews();
    }
    protected void findViews()
    {
        getuserdetails= findViewById(R.id.button8);
        setUpButtonListeners();
    }

    protected void setUpButtonListeners() {
        getuserdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(MainActivityAdmin.this,AdminUserDetails.class);
                startActivity(homeIntent);
            }
        });
    }

}
