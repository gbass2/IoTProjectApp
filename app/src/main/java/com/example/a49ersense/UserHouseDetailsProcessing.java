package com.example.a49ersense;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.a49ersense.DTO.HouseDTO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class UserHouseDetailsProcessing extends AsyncTask<String,Void,String> {

    Context context;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String tsk;
    UserHouseDetailsProcessing(Context ctx){this.context=ctx;}
    @Override
    protected String doInBackground(String... strings) {
        String task=strings[0];
        String userID=strings[1];  //garageID
        String houseID=strings[2]; //garageStatus

        tsk=task;

        preferences=context.getSharedPreferences("MYPREFS",Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString("flag","0");
        editor.commit();
        String houseDetails="http://192.168.43.21/49ersense/housedetails.php";
        String updateGarage="http://192.168.43.21/49ersense/updategarage.php";
        String updateHouseStatus="http://192.168.43.21/49ersense/updateHouseDetails.php";
        String applianceDetails="http://192.168.43.21/49ersense/appliancedetails.php";

        try{

            if(task.equalsIgnoreCase("getHouseDetails")){
                URL url= new URL(houseDetails);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream= httpURLConnection.getOutputStream() ;
                OutputStreamWriter outputStreamWriter= new OutputStreamWriter(outputStream, "UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData= URLEncoder.encode("userID","UTF-8")+"="+URLEncoder.encode(userID,"UTF-8")+"&"
                        +URLEncoder.encode("houseID","UTF-8")+"="+URLEncoder.encode(houseID,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                //get info from database
                InputStream inputStream =httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
                String dataresponse ="";
                String inputLine;



                while((inputLine=bufferedReader.readLine())!=null){
                    dataresponse+=inputLine;

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return (dataresponse);
            }
            if(task.equalsIgnoreCase("updateGarage")){
                URL url= new URL(updateGarage);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream= httpURLConnection.getOutputStream() ;
                OutputStreamWriter outputStreamWriter= new OutputStreamWriter(outputStream, "UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData= URLEncoder.encode("garageID","UTF-8")+"="+URLEncoder.encode(userID,"UTF-8")+"&"
                        +URLEncoder.encode("garageStatus","UTF-8")+"="+URLEncoder.encode(houseID,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                //get info from database
                InputStream inputStream =httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
                String dataresponse ="";
                String inputLine;



                while((inputLine=bufferedReader.readLine())!=null){
                    dataresponse+=inputLine;

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return (dataresponse);
            }if(task.equalsIgnoreCase("updateHouse")){
                URL url= new URL(updateHouseStatus);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream= httpURLConnection.getOutputStream() ;
                OutputStreamWriter outputStreamWriter= new OutputStreamWriter(outputStream, "UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData= URLEncoder.encode("security","UTF-8")+"="+URLEncoder.encode(userID,"UTF-8")+"&"
                        +URLEncoder.encode("houseID","UTF-8")+"="+URLEncoder.encode(houseID,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                //get info from database
                InputStream inputStream =httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
                String dataresponse ="";
                String inputLine;



                while((inputLine=bufferedReader.readLine())!=null){
                    dataresponse+=inputLine;

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return (dataresponse);
            }
            if(task.equalsIgnoreCase("getApplianceDetails")){
                URL url= new URL(applianceDetails);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream= httpURLConnection.getOutputStream() ;
                OutputStreamWriter outputStreamWriter= new OutputStreamWriter(outputStream, "UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData= URLEncoder.encode("userID","UTF-8")+"="+URLEncoder.encode(userID,"UTF-8")+"&"
                        +URLEncoder.encode("houseID","UTF-8")+"="+URLEncoder.encode(houseID,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                //get info from database
                InputStream inputStream =httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
                String dataresponse ="";
                String inputLine;



                while((inputLine=bufferedReader.readLine())!=null){
                    dataresponse+=inputLine;

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return (dataresponse);
            }


        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "0";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s){
        String[] serverResponse = s.split("[,]");
        if(tsk.equalsIgnoreCase("getHouseDetails")){
            if(serverResponse[0]!="0"){
                Intent intent = new Intent(context, MyHouseActivity.class);
                intent.putExtra("serverResponse",s);
                context.startActivity(intent);
            }else {
                Log.d("fetch database failed",serverResponse[0]);
            }
        }
        if(tsk.equalsIgnoreCase("updateGarage")){
            if(serverResponse[0]=="0"){
                Log.d("update garage failed",serverResponse[0]);
            }
        }
        if(tsk.equalsIgnoreCase("updateHouse")){
            if(serverResponse[0]=="0"){
                Log.d("update status failed",serverResponse[0]);
            }
        }
        if(tsk.equalsIgnoreCase("getApplianceDetails")){
            if(serverResponse[0]!="0"){
                Intent intent = new Intent(context, ManageAppliancesActivity.class);
                intent.putExtra("serverResponse",s);
                context.startActivity(intent);
            }else {
                Log.d("fetch appliances failed",serverResponse[0]);
            }
        }

    }

}
