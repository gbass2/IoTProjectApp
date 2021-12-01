package com.example.a49ersense;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a49ersense.DTO.FloorDTO;
import com.example.a49ersense.DTO.HouseDTO;

import java.util.ArrayList;

public class FloorAdapter extends RecyclerView.Adapter<FloorAdapter.ViewHolder>{

    String houseID;
    ArrayList<FloorDTO> fs;
    Context context;
    loadFloorPage load;

    public FloorAdapter(Context context, ArrayList<FloorDTO> list, String id, loadFloorPage obj){
        this.context=context;
        this.fs=list;
        this.houseID=id;
        this.load=obj;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        Button levelButton;

        ViewHolder(View itemView){
            super(itemView);
            levelButton = itemView.findViewById(R.id.floorButton);
        }
    }

    @Override
    public int getItemCount() {
        return fs.size();
    }

    @NonNull
    @Override
    public FloorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.floor_adapter,parent,false);
        FloorAdapter.ViewHolder viewHolder = new FloorAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FloorAdapter.ViewHolder holder, final int position) {
        final FloorDTO f = fs.get(position);
        holder.levelButton.setText(f.getFloorNO());
        holder.levelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                load.loadPage(f, houseID);
            }
        });
    }

    public interface loadFloorPage{
        void loadPage(FloorDTO floor, String houseID);

    }



}
