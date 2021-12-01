package com.example.a49ersense;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a49ersense.DTO.ApplianceDTO;

import java.util.ArrayList;

public class ApplianceAdapter extends RecyclerView.Adapter<ApplianceAdapter.ViewHolder> {

    ArrayList<ApplianceDTO> applianceDTO = new ArrayList<>();
    Context context;

    public ApplianceAdapter(Context context, ArrayList<ApplianceDTO> app) {
        this.context=context;
        this.applianceDTO=app;
    }

    @NonNull
    @Override
    public ApplianceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.appliance_adapter,parent,false);
        ApplianceAdapter.ViewHolder viewHolder = new ApplianceAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.appliance_name.setText(applianceDTO.get(position).getApplianceName());

        if(applianceDTO.get(position).getStatus().equalsIgnoreCase("off")){
            holder.appliance_status.setText(R.string.status_off);
            holder.appliance_status.setTextColor(ContextCompat.getColor(context, R.color.alertRed));
        }else{
            holder.appliance_status.setText(R.string.status_off);
            holder.appliance_status.setTextColor(ContextCompat.getColor(context, R.color.alertGreen));
        }
    }

    @Override
    public int getItemCount() {
        return applianceDTO.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView appliance_name, appliance_status;

        ViewHolder(View itemView){
            super(itemView);
            appliance_name = itemView.findViewById(R.id.appliance_adapter_appliance_name);
            appliance_status = itemView.findViewById(R.id.appliance_adapter_appliance_status);
        }
    }
}
