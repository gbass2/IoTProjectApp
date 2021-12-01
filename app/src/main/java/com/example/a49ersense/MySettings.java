package com.example.a49ersense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MySettings extends AppCompatActivity {
    Button getdetails;
    TextView username;
    EditText password;
    EditText emailid;
    EditText phone;
    EditText address;
    Button updatedetails;
    String usrname;
    SharedPreferences preferences;
    private final String TAG="bhavin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_settings);
        findViews();
        preferences=this.getSharedPreferences("MYPREFS", Context.MODE_PRIVATE);
        String mName = preferences.getString("username","ERROR getting name");
        username.setText("Welcome "+mName);
        usrname=mName;
        String mPassword= preferences.getString("password","");
        password.setText(mPassword);
        String mEmailid = preferences.getString("emailid","");
        emailid.setText(mEmailid);
        String  mPhone= preferences.getString("phone","");
        phone.setText(mPhone);
        String mAddress= preferences.getString("address","");
        address.setText(mAddress);
    }

    protected void findViews() {
        getdetails = findViewById(R.id.getDetails);
        username=findViewById(R.id.usernameMySettings);
        password=findViewById(R.id.passMySettings);
        emailid=findViewById(R.id.emailMySettings);
        phone=findViewById(R.id.phoneMySettings);
        address=findViewById(R.id.addressMySettings);
        updatedetails=findViewById(R.id.button6);
        setUpButtonListeners();
    }

    protected void setUpButtonListeners()
    {
        getdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username.setVisibility(View.VISIBLE);
                password.setVisibility(View.VISIBLE);
                emailid.setVisibility(View.VISIBLE);
                phone.setVisibility(View.VISIBLE);
                address.setVisibility(View.VISIBLE);
                updatedetails.setVisibility(View.VISIBLE);



            }
        });
        updatedetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username1=usrname;
                String password1= password.getText().toString();
                String emailid1= emailid.getText().toString();
                String phone1 = phone.getText().toString();
                String address1= address.getText().toString();
                String task= "update";
                BackgroundTask backgroundTask= new BackgroundTask(MySettings.this);
                backgroundTask.execute(task,password1,emailid1,phone1,address1,username1);
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}