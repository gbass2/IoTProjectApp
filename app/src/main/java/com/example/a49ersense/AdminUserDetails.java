package com.example.a49ersense;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

public class AdminUserDetails extends AppCompatActivity {
    ListView listView;
    List<String>userlist;

    public static  final String TAG="userdetails";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_details);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        listView=findViewById(R.id.list_item2);
        Intent intent = getIntent();
        userlist = intent.getStringArrayListExtra("userlist");
        Log.d(TAG,userlist.toString());
        ArrayAdapter<String> arrayAdapter =  new ArrayAdapter<>(AdminUserDetails.this,android.R.layout.simple_list_item_1,userlist);
        listView.setAdapter(arrayAdapter);
//        setUpButtonListeners();


    }

    protected void setUpButtonListeners() {
        listView.setOnItemClickListener((array, view, position, id) -> {
            String username = (String)array.getItemAtPosition(position);
            String task="adminenergybreakdown";
            BackgroundTask backgroundTask = new BackgroundTask(AdminUserDetails.this);
            backgroundTask.execute(task,username);
        });
    }
}
