package com.example.a49ersense;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a49ersense.DTO.LightsDTO;

import java.util.ArrayList;

public class LightAdapter extends RecyclerView.Adapter<LightAdapter.ViewHolder> {

    ArrayList<LightsDTO> ldto;
    Context context;
    updateLight update;
    String status;

    public LightAdapter(Context context, ArrayList<LightsDTO> list, updateLight obj) {
        this.context = context;
        this.ldto = list;
        this.update=obj;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView lightNo;
        Switch lightSwitch;
        SeekBar dimmerLevel;

        ViewHolder(View itemView){
            super(itemView);
            lightNo = itemView.findViewById(R.id.lightNo);
            lightSwitch = itemView.findViewById(R.id.lightSwitch);
            dimmerLevel = itemView.findViewById(R.id.dimmerLvl);
        }
    }

    @Override
    public int getItemCount() {
        return ldto.size();
    }

    @NonNull
    @Override
    public LightAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.light_adapter,parent,false);
        LightAdapter.ViewHolder viewHolder = new LightAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LightAdapter.ViewHolder holder, int position) {
        final LightsDTO ls = ldto.get(position);
        holder.lightNo.setText("Light No "+ls.getLightID());

        if(ls.getStatus()){
            holder.lightSwitch.setChecked(true);
            status="1";
        }else{
            holder.lightSwitch.setChecked(false);
            status="0";
        }

        switch (ls.getDimmerLevel()){
            case "1":holder.dimmerLevel.setProgress(0);
                break;
            case "2":holder.dimmerLevel.setProgress(1);
                break;
            case "3":holder.dimmerLevel.setProgress(2);
                break;
            case "4":holder.dimmerLevel.setProgress(3);
                break;
            case "5":holder.dimmerLevel.setProgress(4);
                break;
                default: holder.dimmerLevel.setProgress(3);
        }

        holder.lightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked && buttonView.isPressed()){
                    ls.setStatus(true);
                    status="1";
                }
                if(!isChecked && buttonView.isPressed()){
                    ls.setStatus(false);
                    status="0";
                }

                update.updateL(ls.getLightID(),status,ls.getDimmerLevel());
            }
        });

        holder.dimmerLevel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress==0){
                    ls.setDimmerLevel("1");
                }
                if(progress==1){
                    ls.setDimmerLevel("2");
                }
                if(progress==2){
                    ls.setDimmerLevel("3");
                }
                if(progress==3){
                    ls.setDimmerLevel("4");
                }
                if(progress==4){
                    ls.setDimmerLevel("5");
                }
                update.updateL(ls.getLightID(),status,ls.getDimmerLevel());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public interface updateLight{
        void updateL(String lightID, String lightStatus, String dimmerLevel);
    }
}
