package com.example.a49ersense;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class WeatherAdapter extends ArrayAdapter<Weather>
{
    Weather weather=new Weather();
    WeatherAdapter(@NonNull Context context, ArrayList<Weather> weatherArrayList)
    {
        super(context, 0, weatherArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Weather weather = getItem(position);

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView dateTextView      = convertView.findViewById(R.id.tvDate);
        // what is usage of tempTextView
        TextView tempTextView      = convertView.findViewById(R.id.tvTemp);
        TextView backdayTextView   = convertView.findViewById(R.id.backday);
        TextView backnightTextView = convertView.findViewById(R.id.backnight);
        TextView minTempTextView   = convertView.findViewById(R.id.minTemp);
        TextView maxTempTextView   = convertView.findViewById(R.id.maxTemp);

        if (weather != null)
        {
            dateTextView.setText(weather.getDate());
            backdayTextView.setText(weather.getBackgroundDay());
            backnightTextView.setText(weather.getBackgroundNight());
            minTempTextView.setText(weather.getMinTemp());
            maxTempTextView.setText(weather.getMaxTemp());

            if(weather.getBackgroundDay().equals("Sunny")){
                tempTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.sunny,0,0,0);
            }
            if(weather.getBackgroundDay().equals("Mostly Sunny")){
                tempTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mostlysunny,0,0,0);
            }
            if(weather.getBackgroundDay().equals("Partly Sunny")){
                tempTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.partlysunny,0,0,0);
            }
            if(weather.getBackgroundDay().equals("Intermittent Clouds")){
                tempTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.intermittentclouds,0,0,0);
            }
            if(weather.getBackgroundDay().equals("Hazy Sunshine")){
                tempTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.hazysunshine,0,0,0);
            }
            if(weather.getBackgroundDay().equals("Mostly Cloudy")){
                tempTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mostlycloudly,0,0,0);
            }
            if(weather.getBackgroundDay().equals("Cloudy")){
                tempTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.cloudy,0,0,0);
            }
        }

        return convertView;
    }
}
