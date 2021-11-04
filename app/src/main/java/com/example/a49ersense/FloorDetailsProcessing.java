package com.example.a49ersense;

import android.content.Context;import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

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

public class FloorDetailsProcessing extends AsyncTask<String,Void,String> {

    Context context;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    FloorDetailsProcessing(Context ctx){this.context=ctx;}
    //String ip="192.168.43.146";
    String task;
    String msg,status;
    ClientSocket clientSocket = new ClientSocket();

    @Override
    protected String doInBackground(String... strings) {
        String tsk=strings[0];
        String floorID=strings[1]; //lightid
        String floorNO=strings[3]; //controlTemp,dimmerlevel
        String houseID=strings[2]; //lightstatus

        task=tsk;
        preferences=context.getSharedPreferences("MYPREFS",Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString("flag","0");
        editor.commit();
        String floorDetails="http://192.168.43.21/49ersense/floordetails.php";
        String updateThermoControl="http://192.168.43.21/49ersense/updateControlTemp.php";
        String updateFan="http://192.168.43.21/49ersense/updateFan.php";
        String updateMode="http://192.168.43.21/49ersense/updateMode.php";
        String updateLight="http://192.168.43.21/49ersense/updateLight.php";

        try{
            if(task.equalsIgnoreCase("getFloorDetails")){
                URL url= new URL(floorDetails);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream= httpURLConnection.getOutputStream() ;
                OutputStreamWriter outputStreamWriter= new OutputStreamWriter(outputStream, "UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData= URLEncoder.encode("floorID","UTF-8")+"="+URLEncoder.encode(floorID,"UTF-8")+"&"
                        +URLEncoder.encode("floorNO","UTF-8")+"="+URLEncoder.encode(floorNO,"UTF-8")+"&"+
                        URLEncoder.encode("houseID","UTF-8")+"="+URLEncoder.encode(houseID,"UTF-8");
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
                Log.d("db result",dataresponse);
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return (dataresponse);

            }

            if(task.equalsIgnoreCase("updateControlTemp")){
                URL url= new URL(updateThermoControl);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream= httpURLConnection.getOutputStream() ;
                OutputStreamWriter outputStreamWriter= new OutputStreamWriter(outputStream, "UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData= URLEncoder.encode("houseID","UTF-8")+"="+URLEncoder.encode(houseID,"UTF-8")+"&"
                        +URLEncoder.encode("floorID","UTF-8")+"="+URLEncoder.encode(floorID,"UTF-8")+"&"+
                        URLEncoder.encode("controlTemp","UTF-8")+"="+URLEncoder.encode(floorNO,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                msg=floorNO;
                //clientSocket.sendMessage("Control temp changed to"+floorNO);
                //get info from database
                InputStream inputStream =httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
                String dataresponse ="";
                String inputLine;



                while((inputLine=bufferedReader.readLine())!=null){
                    dataresponse+=inputLine;

                }
                Log.d("db result",dataresponse);
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return (dataresponse);

            }

            if(task.equalsIgnoreCase("updateFanMode")){
                URL url= new URL(updateFan);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream= httpURLConnection.getOutputStream() ;
                OutputStreamWriter outputStreamWriter= new OutputStreamWriter(outputStream, "UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData= URLEncoder.encode("houseID","UTF-8")+"="+URLEncoder.encode(houseID,"UTF-8")+"&"
                        +URLEncoder.encode("floorID","UTF-8")+"="+URLEncoder.encode(floorID,"UTF-8")+"&"+
                        URLEncoder.encode("fan","UTF-8")+"="+URLEncoder.encode(floorNO,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                msg=floorNO;
                //clientSocket.sendMessage("Fan mode changed to"+floorNO);
                //get info from database
                InputStream inputStream =httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
                String dataresponse ="";
                String inputLine;



                while((inputLine=bufferedReader.readLine())!=null){
                    dataresponse+=inputLine;

                }
                Log.d("db result",dataresponse);
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return (dataresponse);

            }

            if(task.equalsIgnoreCase("updateMode")){
                URL url= new URL(updateMode);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream= httpURLConnection.getOutputStream() ;
                OutputStreamWriter outputStreamWriter= new OutputStreamWriter(outputStream, "UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData= URLEncoder.encode("houseID","UTF-8")+"="+URLEncoder.encode(houseID,"UTF-8")+"&"
                        +URLEncoder.encode("floorID","UTF-8")+"="+URLEncoder.encode(floorID,"UTF-8")+"&"+
                        URLEncoder.encode("mode","UTF-8")+"="+URLEncoder.encode(floorNO,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                msg=floorNO;
                //clientSocket.sendMessage("Mode changed to"+floorNO);
                //get info from database
                InputStream inputStream =httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
                String dataresponse ="";
                String inputLine;



                while((inputLine=bufferedReader.readLine())!=null){
                    dataresponse+=inputLine;

                }
                Log.d("db result",dataresponse);
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return (dataresponse);

            }

            if(task.equalsIgnoreCase("updateLight")){
                URL url= new URL(updateLight);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream= httpURLConnection.getOutputStream() ;
                OutputStreamWriter outputStreamWriter= new OutputStreamWriter(outputStream, "UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData= URLEncoder.encode("dimmerLevel","UTF-8")+"="+URLEncoder.encode(floorNO,"UTF-8")+"&"
                        +URLEncoder.encode("lightID","UTF-8")+"="+URLEncoder.encode(floorID,"UTF-8")+"&"+
                        URLEncoder.encode("lightStatus","UTF-8")+"="+URLEncoder.encode(houseID,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                msg=floorID;
                status=floorNO;
                //clientSocket.sendMessage("Light"+floorID+" changed to"+floorNO);
                //get info from database
                InputStream inputStream =httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
                String dataresponse ="";
                String inputLine;



                while((inputLine=bufferedReader.readLine())!=null){
                    dataresponse+=inputLine;

                }
                Log.d("db result",dataresponse);
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
//        try {
//            clientSocket.startConnection(ip,1024);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        if(task.equalsIgnoreCase("getFloorDetails")){
            if(serverResponse[0]!="0"){
                Intent intent = new Intent(context, FloorDetailsActivity.class);
                intent.putExtra("serverResponseforFloor",s);
                context.startActivity(intent);
            }else {
                Log.d("fetch database failed",serverResponse[0]);
            }
        }

        if(task.equalsIgnoreCase("updateControlTemp")){
            if(serverResponse[0]=="0"){
                Log.d("update temp failed",serverResponse[0]);
            }
        }

        if(task.equalsIgnoreCase("updateFanMode")){
            if(serverResponse[0]=="0"){
                Log.d("update fan failed",serverResponse[0]);
            }
        }

        if(task.equalsIgnoreCase("updateMode")){
            if(serverResponse[0]=="0"){
                Log.d("update mode failed",serverResponse[0]);
            }
        }

        if(task.equalsIgnoreCase("updateLight")){
            if(serverResponse[0]=="0"){
                Log.d("update light failed",serverResponse[0]);
            }
        }
    }

}
