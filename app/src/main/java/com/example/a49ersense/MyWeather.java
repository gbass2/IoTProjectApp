package com.example.a49ersense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MyWeather extends AppCompatActivity {
    private final  String TAG = "hello";

    // TODO : If following are not used in activity then move the declaration to asynch task
    private ArrayList<Weather> weatherArrayList = new ArrayList<>();
    private ArrayList<String> dateArray = new ArrayList<>();
    private ArrayList<String> minTempArray = new ArrayList<>();
    private ArrayList<String>maxTempArray= new ArrayList<>();
    private ArrayList<String> backgroundDayArray= new ArrayList<>();
    private ArrayList<String>backgroundNightArray= new ArrayList<>();

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_weather);
        findViews();
    }

    protected void findViews()
    {
        listView = findViewById(R.id.list_item1);
        startFetchWeatherDetailsAsyncTask();
    }

    protected void startFetchWeatherDetailsAsyncTask()
    {
        SharedPreferences preferences;
        preferences=this.getSharedPreferences("MYPREFS", Context.MODE_PRIVATE);
        String address= preferences.getString("address","");

        URL keyUrl= null;
        try {
            keyUrl = NetworkUtils.buildURLForLocation(address);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new FetchWeatherDetails().execute(keyUrl);
    }

    // TODO : Please make below class static and pass all the list in execute parameters
    // TODO : Not doing it will cause the memory leak and you should not access activity objects directly.
    private class FetchWeatherDetails extends AsyncTask<URL,Void,String>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... urls) {
//            URL weatherUrl =urls[0];
            URL keyUrl =urls[0];
            String keySearchResults = null;
            String weatherSearchResults=null;
            try {
                keySearchResults = NetworkUtils.getResponseFromHttpUrl(keyUrl);
                Log.d(TAG,"key : " + keySearchResults);
                JSONArray rootObject= new JSONArray(keySearchResults);
                String key = rootObject.getJSONObject(0).getString("Key");

                Log.d(TAG,"key : " + key);
                URL weatherUrl = NetworkUtils.buildURLForWeather(key);
                weatherSearchResults=NetworkUtils.getResponseFromHttpUrl(weatherUrl);
            } catch (IOException | JSONException e1) {
                e1.printStackTrace();
            }

            return weatherSearchResults;
        }

        @Override
        protected void onPostExecute(String weatherSearchResults) {
            if(weatherSearchResults !=null && !weatherSearchResults.equals("")){
                weatherArrayList =parseJSON(weatherSearchResults );

            }
            super.onPostExecute(weatherSearchResults);
        }

        private ArrayList<Weather> parseJSON(String weatherSearchResults) {
            if(weatherArrayList!=null){
                weatherArrayList.clear();
            }
            if(weatherSearchResults!=null){
                try {
                    JSONObject rootObject= new JSONObject(weatherSearchResults);
                    Log.d(TAG,"data:"+rootObject.toString());
                    JSONArray results= rootObject.getJSONArray("DailyForecasts");
                    // TODO : pass the context in constructor and use weakreference

                    for(int i= 0;i<results.length();i++){
                        Weather weather= new Weather();
                        JSONObject resultObj = results.getJSONObject(i);
                        String date = resultObj.getString("Date");
                        dateArray.add(date);
                        weather.setDate(date);
                        JSONObject temperatureObj = resultObj.getJSONObject("Temperature");
                        String minTemp=temperatureObj.getJSONObject("Minimum").getString("Value");
                        minTempArray.add(minTemp);
                        weather.setMinTemp(minTemp);
                        String maxTemp=temperatureObj.getJSONObject("Maximum").getString("Value");
                        maxTempArray.add(maxTemp);
                        weather.setMaxTemp(maxTemp);
                        JSONObject backDayObj= resultObj.getJSONObject("Day");
                        String backday=backDayObj.getString("IconPhrase");
                        backgroundDayArray.add(backday);
                        weather.setBackgroundDay(backday);
                        JSONObject backNightObj =resultObj.getJSONObject("Night");
                        String backnight =backNightObj.getString("IconPhrase");
                        backgroundNightArray.add(backnight);
                        weather.setBackgroundNight(backnight);
                        weatherArrayList.add(weather);

                    }
                    if(weatherArrayList !=null){
                        WeatherAdapter weatherAdapter = new WeatherAdapter(MyWeather.this,weatherArrayList);
                        listView.setAdapter(weatherAdapter);
                    }else {
                        Toast.makeText(getApplicationContext(),"Data invalid",Toast.LENGTH_LONG).show();
                    }
                    return weatherArrayList;

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}

