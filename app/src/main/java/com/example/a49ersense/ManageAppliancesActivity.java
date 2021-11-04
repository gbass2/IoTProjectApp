package com.example.a49ersense;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a49ersense.DTO.ApplianceDTO;

import java.util.ArrayList;

public class ManageAppliancesActivity extends AppCompatActivity {

    final String TAG = "ManageAppActivity";
    Dialog dialog;
    String houseid;
    RecyclerView view_appliances;
    ApplianceAdapter applianceAdapter;
    Button addApplianceBtn, scheduleBtn;
    SpinnerAdapter spinner_adapter;
    String[] app_type_ls;
    String spinner_item;
    ArrayList<ApplianceDTO> app= new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_appliances);
        Intent intent = getIntent();
        String s = intent.getStringExtra("serverResponse");
        String[] serverResponse = s.split("[,]");
        houseid = serverResponse[0];
        for(int i = 1; i<serverResponse.length;i++){
            ApplianceDTO a = new ApplianceDTO();
            a.setApplianceID(serverResponse[i]);
            a.setApplianceType(serverResponse[i+1]);
            a.setApplianceName(serverResponse[i+2]);
            a.setStatus(serverResponse[i+3]);
            app.add(a);
            i=i+3;
        }
        app_type_ls = getResources().getStringArray(R.array.appliance_type);

        addApplianceBtn = findViewById(R.id.add_appliance_button);
        scheduleBtn = findViewById(R.id.schedule_button);
        spinner_adapter = new SpinnerAdapter(getApplicationContext());
        addApplianceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "submit");
                dialog = new Dialog(ManageAppliancesActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.custom_alert_dialog_add_appliance);
                dialog.setCancelable(true);

                final Spinner appTypeSpinner = dialog.findViewById(R.id.applianceType);
                final EditText appNameEditText = dialog.findViewById(R.id.appliance_name);
                Button button = dialog.findViewById(R.id.alert_add_app_btn);
                appTypeSpinner.setAdapter(spinner_adapter);

                appTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        spinner_item = app_type_ls[position];
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Toast.makeText(ManageAppliancesActivity.this, spinner_item + " - " + appNameEditText.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }
        });
        view_appliances = findViewById(R.id.viewAppliance);
        view_appliances.setLayoutManager(new LinearLayoutManager(ManageAppliancesActivity.this));

        applianceAdapter = new ApplianceAdapter(ManageAppliancesActivity.this,app);
        view_appliances.setAdapter(applianceAdapter);
    }

    public class SpinnerAdapter extends BaseAdapter {

        Context context;
        private LayoutInflater mInflater;

        public SpinnerAdapter(Context context) {
            this.context = context;
        }


        @Override
        public int getCount() {
            return app_type_ls.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ListContent holder;
            View v = convertView;
            if (v == null) {
                mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                v = mInflater.inflate(R.layout.row_textview, null);
                holder = new ListContent();
                holder.text = v.findViewById(R.id.txtlarge);

                v.setTag(holder);
            } else {
                holder = (ListContent) v.getTag();
            }

            holder.text.setText(app_type_ls[position]);

            return v;
        }
    }
    static class ListContent {
        TextView text;
    }
}
