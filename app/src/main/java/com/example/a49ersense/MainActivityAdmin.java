package com.example.a49ersense;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivityAdmin extends AppCompatActivity {
    Button getuserdetails;
    ArrayList<String> userlist;
    BackgroundTask backgroundTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_admin);
        String task= "adminuserdetails";
        backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(task);

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
                userlist = new ArrayList<String>(backgroundTask.arraylist);
                homeIntent.putExtra("userlist", userlist);
                startActivity(homeIntent);
            }
        });
    }

}
