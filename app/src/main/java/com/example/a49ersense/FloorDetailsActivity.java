package com.example.a49ersense;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a49ersense.DTO.FloorDTO;
import com.example.a49ersense.DTO.LightsDTO;

import java.util.ArrayList;

public class FloorDetailsActivity extends AppCompatActivity implements LightAdapter.updateLight {

    RecyclerView view_lights;
    SeekBar mode;
    Button plus,minus;
    Switch fan;
    LightAdapter lightAdapter;
    TextView controlTemp, currentTemp, floorHeader;
    FloorDTO floor = new FloorDTO();
    ArrayList<LightsDTO> ls = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_details);
        Intent intent = getIntent();
        String s = intent.getStringExtra("serverResponseforFloor");
        String[] serverResponse = s.split("[,]");
        final String houseid= serverResponse[0];
        floor.setFloorID(serverResponse[1]);
        floor.setFloorNO(serverResponse[2]);
        floor.settMode(serverResponse[3]);
        floor.settFan(serverResponse[4]);
        floor.settCurrent(serverResponse[5]);
        floor.settControl(serverResponse[6]);
        if(serverResponse[7].equalsIgnoreCase("1")){
            floor.settStatus(true);
        }else{
            floor.settStatus(false);
        }

        for(int i=8; i < serverResponse.length;i++){
            LightsDTO lights = new LightsDTO();
            lights.setLightID(serverResponse[i]);
            if(serverResponse[i+1].equalsIgnoreCase("1")){
                lights.setStatus(true);
            }else{
                lights.setStatus(false);
            }
            lights.setDimmerLevel(serverResponse[i+2]);
            i=i+2;
            ls.add(lights);
        }

        view_lights= findViewById(R.id.viewLights);
        mode= findViewById(R.id.seekBar);
        plus=findViewById(R.id.plusbutton);
        minus=findViewById(R.id.minusbutton);
        fan=findViewById(R.id.switchFan);
        controlTemp=findViewById(R.id.controlTempLabel);
        currentTemp=findViewById(R.id.currentTempLabel);
        floorHeader=findViewById(R.id.floorHeader);

        floorHeader.setText(floor.getFloorNO());
        controlTemp.setText(floor.gettControl());
        currentTemp.setText(floor.gettCurrent());
        if(floor.gettMode().equalsIgnoreCase("Cool")){
            mode.setProgress(0);
        }else if(floor.gettMode().equalsIgnoreCase("Off")){
            mode.setProgress(1);
        }else{
            mode.setProgress(2);
        }
        if(floor.gettFan().equalsIgnoreCase("Auto")){
            fan.setChecked(true);
        }else{
            fan.setChecked(false);
        }

        view_lights.setLayoutManager(new LinearLayoutManager(FloorDetailsActivity.this));
        lightAdapter = new LightAdapter(FloorDetailsActivity.this,ls,this);
        view_lights.setAdapter(lightAdapter);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp=Integer.parseInt(floor.gettControl());
                temp++;
                floor.settControl(Integer.toString(temp));
                controlTemp.setText(floor.gettControl());
                FloorDetailsProcessing floorprocessing = new FloorDetailsProcessing(FloorDetailsActivity.this);
                floorprocessing.execute("updateControlTemp",floor.getFloorID(),houseid,floor.gettControl());
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp=Integer.parseInt(floor.gettControl());
                temp--;
                floor.settControl(Integer.toString(temp));
                controlTemp.setText(floor.gettControl());
                FloorDetailsProcessing floorprocessing = new FloorDetailsProcessing(FloorDetailsActivity.this);
                floorprocessing.execute("updateControlTemp",floor.getFloorID(),houseid,floor.gettControl());
            }
        });

        fan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked && buttonView.isPressed()){
                    floor.settFan("Auto");
                }
                if(!isChecked && buttonView.isPressed()){
                    floor.settFan("Off");
                }
                FloorDetailsProcessing floorprocessing = new FloorDetailsProcessing(FloorDetailsActivity.this);
                floorprocessing.execute("updateFanMode",floor.getFloorID(),houseid,floor.gettFan());
            }
        });

        mode.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress==0){
                    floor.settMode("Cool");
                }
                if(progress==1){
                    floor.settMode("Off");
                }
                if(progress==2){
                    floor.settMode("Auto");
                }
                FloorDetailsProcessing floorprocessing = new FloorDetailsProcessing(FloorDetailsActivity.this);
                floorprocessing.execute("updateMode",floor.getFloorID(),houseid,floor.gettMode());
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
    public void updateL(String lightID,String lightStatus, String dimmerLevel) {

        FloorDetailsProcessing floorprocessing = new FloorDetailsProcessing(FloorDetailsActivity.this);
        floorprocessing.execute("updateLight",lightID,lightStatus,dimmerLevel);
    }

}
