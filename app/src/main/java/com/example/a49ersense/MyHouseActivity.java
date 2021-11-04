package com.example.a49ersense;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.a49ersense.DTO.FloorDTO;
import com.example.a49ersense.DTO.GarageDTO;
import com.example.a49ersense.DTO.HouseDTO;
import com.example.a49ersense.DTO.LightsDTO;

import java.util.ArrayList;
import java.util.List;

public class MyHouseActivity extends AppCompatActivity implements FloorAdapter.loadFloorPage, GarageAdapter.updateGarage {

    TextView frntLockLabel,statuslabel,backLockLabel,garageLockLabel;
    SeekBar seekStatus;
    RecyclerView view_garage, view_floors;
    HouseDTO house = new HouseDTO();
    ArrayList<FloorDTO> fs = new ArrayList<>();
    ArrayList<GarageDTO> gs = new ArrayList<>();
    GarageAdapter garageAdapter;
    FloorAdapter  floorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_house);
        Intent intent = getIntent();
        String s = intent.getStringExtra("serverResponse");
        String[] serverResponse = s.split("[,]");
        house.setHouseID(serverResponse[0]);
        house.setSecurityStatus(serverResponse[1]);
        if(serverResponse[2].equalsIgnoreCase("1")){
            house.setFrontLockStatus(true);
        }else{
            house.setFrontLockStatus(false);
        }
        if(serverResponse[3].equalsIgnoreCase("1")){
            house.setBackLockStatus(true);
        }else{
            house.setBackLockStatus(false);
        }
        if(serverResponse[4].equalsIgnoreCase("1")){
            house.setGarageLockStatus(true);
        }else{
            house.setGarageLockStatus(false);
        }

        house.setFloors(serverResponse[5]);
        house.setGarageDoors(serverResponse[6]);
        int index = 7;
        for(int i=0;i<Integer.parseInt(house.getGarageDoors());i++){
            GarageDTO garage = new GarageDTO();
            garage.setGarageID(serverResponse[index]);
            garage.setDoorStatus(serverResponse[index+1]);
            garage.setLockStatus(serverResponse[index+2]);
            garage.setGarageDoorNo("Garage "+serverResponse[index+3]);
            gs.add(garage);
            index = index+4;
        }

        for(int j=0; j<Integer.parseInt(house.getFloors());j++){
            FloorDTO floor = new FloorDTO();
            floor.setFloorID(serverResponse[index]);
            floor.setFloorNO("Floor "+serverResponse[index+1]);
            fs.add(floor);
            index =index +2;
        }

        house.setGarageDTO(gs);
        house.setFloorDTO(fs);

        statuslabel = findViewById(R.id.statusLabel);
        frntLockLabel = findViewById(R.id.frntLockLabel);
        backLockLabel =findViewById(R.id.bckLockLabel);
        garageLockLabel =findViewById(R.id.grgLockLabel);
        statuslabel.setText(house.getSecurityStatus());
        seekStatus = findViewById(R.id.seekStatus);


        if(house.getFrontLockStatus()){
            frntLockLabel.setText("Locked");
        }else {
            frntLockLabel.setText("Unlocked");
        }
        if(house.getBackLockStatus()){
            backLockLabel.setText("Locked");
        }else {
            backLockLabel.setText("Unlocked");
        }
        if(house.getGarageLockStatus()){
            garageLockLabel.setText("Locked");
        }else {
            garageLockLabel.setText("Unlocked");
        }

        if(house.getSecurityStatus().equalsIgnoreCase("Armed Home")){
            seekStatus.setProgress(2);
        }
        if(house.getSecurityStatus().equalsIgnoreCase("Armed Away")){
            seekStatus.setProgress(1);
        }
        if(house.getSecurityStatus().equalsIgnoreCase("Unarmed")){
            seekStatus.setProgress(0);
        }

        view_garage = findViewById(R.id.viewGarage);
        view_floors = findViewById(R.id.viewFloors);

        view_garage.setLayoutManager(new LinearLayoutManager(MyHouseActivity.this));

        view_floors.setLayoutManager(new LinearLayoutManager(MyHouseActivity.this));

        //fetch data from database

        //adapter here
        garageAdapter = new GarageAdapter(MyHouseActivity.this,gs,this);
        view_garage.setAdapter(garageAdapter);

        floorAdapter = new FloorAdapter(MyHouseActivity.this,fs,house.getHouseID(),this);
        view_floors.setAdapter(floorAdapter);

        seekStatus.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress==0){
                    statuslabel.setText("Unarmed");
                    house.setSecurityStatus("Unarmed");
                }
                if(progress==1){
                    statuslabel.setText("Armed Away");
                    house.setSecurityStatus("Armed Away");
                }
                if(progress==2){
                    statuslabel.setText("Armed Home");
                    house.setSecurityStatus("Armed Home");
                }
                UserHouseDetailsProcessing housedetails =  new UserHouseDetailsProcessing(MyHouseActivity.this);
                housedetails.execute("updateHouse",house.getSecurityStatus(),house.getHouseID());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void loadPage(FloorDTO floor,String houseID) {

        FloorDetailsProcessing floorprocessing = new FloorDetailsProcessing(MyHouseActivity.this);
        floorprocessing.execute("getFloorDetails",floor.getFloorID(),houseID,floor.getFloorNO());

    }

    public void updateG(String garageID,String garageStatus) {

        UserHouseDetailsProcessing housedetails = new UserHouseDetailsProcessing(MyHouseActivity.this);
        housedetails.execute("updateGarage",garageID,garageStatus);
    }
}
