package com.example.a49ersense;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NetworkUtils {
    private final static  String WeatherDB_Base_URL= "http://dataservice.accuweather.com/forecasts/v1/daily/5day/";
    private final static  String API_KEY="rAZSSB1ggDRTUacGazGP15Qq4PVL9mAA";
    private final static  String PARAM_API_KEY="apikey";
    private static final String TAG="hello";

    public static URL buildURLForLocation(String address) throws IOException, JSONException {
        Uri builtUri = Uri.parse("http://dataservice.accuweather.com/locations/v1/postalcodes/US/search").buildUpon()
                .appendQueryParameter(PARAM_API_KEY, API_KEY)
                .appendQueryParameter("q", address)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static URL buildURLForWeather(String key) throws IOException, JSONException {
        Uri builtUri = Uri.parse(WeatherDB_Base_URL+key).buildUpon()
                .appendQueryParameter(PARAM_API_KEY,API_KEY)
                .build();
        URL url = null;
        try {
            url= new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.d(TAG,"buildUrlForWeather:url:"+url);
        return url;
    }
    public static String getResponseFromHttpUrl(URL url) throws IOException{
        HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
        try {
            InputStream in= httpURLConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if(hasInput){
                return scanner.next();
            }else {
                return null;
            }
        }finally {
            httpURLConnection.disconnect();
        }
    }
}
