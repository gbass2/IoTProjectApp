package com.example.a49ersense;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a49ersense.DTO.GarageDTO;

import java.util.ArrayList;

public class GarageAdapter extends RecyclerView.Adapter<GarageAdapter.ViewHolder> {

    ArrayList<GarageDTO> gdto = new ArrayList<>();
    Context context;
    updateGarage update;

    public GarageAdapter(Context context, ArrayList<GarageDTO> list, updateGarage obj){
        this.context=context;
        this.gdto=list;
        this.update=obj;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView garageNo, garageDoorStatus;
        Switch garageSwitch;

        ViewHolder(View itemView){
            super(itemView);
            garageNo = itemView.findViewById(R.id.garageNo);
            garageDoorStatus = itemView.findViewById(R.id.garageDoorStatus);
            garageSwitch = itemView.findViewById(R.id.garageSwitch);
        }
    }

    @Override
    public int getItemCount() {
        return gdto.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.garage_adapter,parent,false);
        ViewHolder viewHolder = new GarageAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final GarageAdapter.ViewHolder holder, final int position) {
        final GarageDTO gs = gdto.get(position);
        holder.garageNo.setText(gs.getGarageDoorNo());

        if(gs.getDoorStatus().equalsIgnoreCase("Open")){
            holder.garageSwitch.setChecked(true);
        }else{
            holder.garageSwitch.setChecked(false);
        }

        holder.garageDoorStatus.setText(gs.getDoorStatus());

        holder.garageSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked && buttonView.isPressed()){
                    gs.setDoorStatus("Open");
                }
                if(!isChecked && buttonView.isPressed()){
                    gs.setDoorStatus("Closed");
                }

                holder.garageDoorStatus.setText(gs.getDoorStatus());
                update.updateG(gs.getGarageID(),gs.getDoorStatus());

            }
        });
    }

    public interface updateGarage{
        void updateG(String garageID, String garageStatus);

    }
}
