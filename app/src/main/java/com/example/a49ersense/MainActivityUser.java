package com.example.a49ersense;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.a49ersense.DTO.HouseDTO;

public class MainActivityUser extends AppCompatActivity
{
    SharedPreferences preferences;
    Button house_details;
    Button weather_details;
    Button video_feed;
    Button energy_details;
    Button my_settings;
    Button manageAppliances;
    HouseDTO house = new HouseDTO();

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences= this.getSharedPreferences("MYPREFS", Context.MODE_PRIVATE);

        String userID = preferences.getString("username","");
        String houseID = preferences.getString("houseID","");
        house.setHouseID(houseID);
        house.setUserID(userID);
        setContentView(R.layout.activity_main_user);
        findViews();

    }

    protected void findViews()
    {
        house_details   = findViewById(R.id.houseDetails);
        weather_details = findViewById(R.id.weatherDetails);
        video_feed      = findViewById(R.id.videoFeed);
        energy_details  = findViewById(R.id.energyBreakDown);
        my_settings     = findViewById(R.id.mySettings);
        manageAppliances = findViewById(R.id.appliances);

        setUpButtonListeners();
    }

    protected void setUpButtonListeners()
    {
        house_details.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivityUser.this, MyHouseActivity.class));
                UserHouseDetailsProcessing houseDetailsProcessing = new UserHouseDetailsProcessing(MainActivityUser.this);
                houseDetailsProcessing.execute("getHouseDetails",house.getUserID(),house.getHouseID());
            }
        });

        my_settings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityUser.this,MySettings.class));
            }
        });
        energy_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityUser.this,EnergyBreakdown.class));
            }
        });

        weather_details.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityUser.this,MyWeather.class));
            }
        });

        video_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityUser.this, VideoCaptureActivity.class));
            }
        });

        manageAppliances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserHouseDetailsProcessing houseDetailsProcessing = new UserHouseDetailsProcessing(MainActivityUser.this);
                houseDetailsProcessing.execute("getApplianceDetails",house.getUserID(),house.getHouseID());
            }
        });
    }

}
