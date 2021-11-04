package com.example.a49ersense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity
{
    Button signup;
    Button login;
    Button admin;
    EditText username1;
    EditText password1;

    SharedPreferences preferences;
    Package pkg = new Package();

    private final String TAG = "data1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();
    }

    protected void findViews()
    {
        username1 = findViewById(R.id.editText2);
        password1 = findViewById(R.id.editText3);
        login     = findViewById(R.id.button2);
        signup    = findViewById(R.id.button3);
        admin = findViewById(R.id.adminButton);

        setUpButtonListeners();
    }

    protected void setUpButtonListeners()
    {
        preferences = this.getSharedPreferences("MYPREFS", Context.MODE_PRIVATE);

        signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Signup.class));

            }
        });
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String username=username1.getText().toString();
                pkg.setUsr(username1.getText().toString());

                String password=password1.getText().toString();

                String task = "userlogin";
                BackgroundTask backgroundTask= new BackgroundTask(Login.this);
                username1.setText("");
                password1.setText("");
                backgroundTask.execute(task,username,password);
//                startActivity(new Intent(Login.this, MainActivityUser.class));
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=username1.getText().toString();
                pkg.setUsr(username1.getText().toString());

                String password=password1.getText().toString();

                String task = "useradmin";
                BackgroundTask backgroundTask= new BackgroundTask(Login.this);
                username1.setText("");
                password1.setText("");
                backgroundTask.execute(task,username,password);

            }
        });


    }
}