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
        String task= "adminuserdetails";
        BackgroundTask backgroundTask= new BackgroundTask(this);

        backgroundTask.execute(task);
        ArrayList<String> userlist1= new ArrayList<>();
        ArrayAdapter<String> arrayAdapter =  new ArrayAdapter<String>(AdminUserDetails.this,android.R.layout.simple_list_item_1,userlist1);
        listView.setAdapter(arrayAdapter);
        setUpButtonListeners();
//        ArrayList<String> userlist= new ArrayList<String>(backgroundTask1.arraylist);
//        Log.d(TAG,userlist.toString());


    }

    protected void setUpButtonListeners() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> array, View view, int position, long id) {
                String username = (String)array.getItemAtPosition(position);
                String task="adminenergybreakdown";
                BackgroundTask backgroundTask = new BackgroundTask(AdminUserDetails.this);
                backgroundTask.execute(task,username);
            }
        });
    }

    public void getArrayList(List<String> arrayList){
        userlist= arrayList;
        Log.d(TAG,userlist.toString());
//        ArrayAdapter<String> arrayAdapter =  new ArrayAdapter<String>(AdminUserDetails.this,android.R.layout.simple_list_item_1,userlist);
//        listView.setAdapter(arrayAdapter);

    }

}
