package com.example.a49ersense;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity
{
    SharedPreferences preferences;
    EditText username1;
    EditText userid1;
    EditText name1;
    EditText password1;
    EditText emailid1;
    EditText phone1;
    EditText address1;
    Button signup;
    CheckBox admin;
    Context context;
    SharedPreferences.Editor editor;
    Package pkg= new Package();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        findViews();

    }

    protected void findViews()
    {
        name1     = findViewById(R.id.editText);
        username1 = findViewById(R.id.editText4);
        password1 = findViewById(R.id.editText11);
        signup    = findViewById(R.id.button4);
        emailid1  = findViewById(R.id.editText5);
        phone1    = findViewById(R.id.editText6);
        address1  = findViewById(R.id.editText7);
        userid1   = findViewById(R.id.editText10);
        admin     = findViewById(R.id.checkBox);
        setUpButtonListeners();
    }

    protected void setUpButtonListeners()
    {
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (admin.isChecked()) {
                    userid1.setVisibility(View.VISIBLE);
                } else {

                    userid1.setVisibility(View.INVISIBLE);

                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final String fullname = name1.getText().toString();
                final String username = username1.getText().toString();
                final String password = password1.getText().toString();
                final String emailid  = emailid1.getText().toString();
                final String phone    = phone1.getText().toString();
                final String address  = address1.getText().toString();
                final String userid;

                if (admin.isChecked()) {
                    userid = userid1.getText().toString();
                    String task = "registeradmin";
                    pkg.setUsr(task);

                } else {
                    userid1.setText("user");
                    userid = userid1.getText().toString();
                    String task = "registeruser";
                    pkg.setUsr(task);

                }

                BackgroundTask backgroundTask = new BackgroundTask(Signup.this);

                name1.setText("");
                username1.setText("");
                password1.setText("");
                emailid1.setText("");
                phone1.setText("");
                address1.setText("");
                userid1.setText("");

                //execute the task
                //passes the params to the backgroundTask (param[0],param[1],param[2])
                // TODO : should have a POJO for these all parameters
                String task= pkg.getUsr();
                backgroundTask.execute(task, fullname, username, password, emailid, phone, address, userid);
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}
