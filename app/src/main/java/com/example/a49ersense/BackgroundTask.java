package com.example.a49ersense;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BackgroundTask extends AsyncTask<String,Void,String> {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;
    Package pkg= new Package();
    private final String TAG="helloer";
    String flag1;
    List<String> arraylist;
    List<String> arraylist1;
    BackgroundTask(Context ctx){
        this.context=ctx;
    }
    @Override
    protected String doInBackground(String... params) {
//        Log.d(TAG,params[0]);
        preferences=context.getSharedPreferences("MYPREFS",Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString("flag","0");
        editor.commit();
        String urlLogin = "http://10.211.55.3/49ersense/login.php";
        String urlRegister ="http://10.211.55.3/49ersense/register.php";
        String urlUpdate="http://10.211.55.3/49ersense/update_my_settings.php";
        String urlRegisterAdmin="http://10.211.55.3/49ersense/admin/register.php";
        String urlLoginAdmin="http://10.211.55.3/49ersense/admin/login.php";
        String urlAdminUserDetails="http://10.211.55.3/49ersense/admin/user_details.php";
        String urlAdminEnerngyBreakdown="http://10.211.55.3/49ersense/energyBreakdown.php";
        String task=params[0];
        Log.d(TAG,params[0]);
        flag1=task;
        if(task.equals("adminenergybreakdown")){
            final String username = params[1];
            try {
               URL url = new URL(urlAdminEnerngyBreakdown);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream= httpURLConnection.getOutputStream() ;
                OutputStreamWriter outputStreamWriter= new OutputStreamWriter(outputStream, "UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData=URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
//                Get information from database
                InputStream inputStream =httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
                String dataresponse ="";
                StringBuilder result=new StringBuilder();
                String inputLine;
                while((inputLine=bufferedReader.readLine())!=null){
                    dataresponse+=inputLine;

                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                editor.putString("flag","adminenergybreakdown");
                editor.commit();
                return (dataresponse);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if(task.equals("adminuserdetails")){
            try{
                URL url = new URL(urlAdminUserDetails);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                // Get Information from Database
                InputStream inputStream =httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
                String dataresponse ="";
                StringBuilder result=new StringBuilder();
                String inputLine;

                while((inputLine=bufferedReader.readLine())!=null){
                    Log.d("adminuserdetails", inputLine);
                    dataresponse+=inputLine;

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                editor.putString("flag","adminuserdetails");
                editor.commit();
                return (dataresponse);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(task.equals("update")){
            final String password1=params[1];
            final String emailid1=params[2];
            final String phone1= params[3];
            final String address1= params[4];
            final String username1=params[5];
            Log.d(TAG,phone1);
            try{
                URL url= new URL(urlUpdate);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter= new OutputStreamWriter(outputStream,"UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter((outputStreamWriter));
                String myData=URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password1,"UTF-8")+"&"
                        +URLEncoder.encode("emailid","UTF-8")+"="+URLEncoder.encode(emailid1,"UTF-8")+"&"
                        +URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(phone1,"UTF-8")+"&"
                        +URLEncoder.encode("address","UTF-8")+"="+URLEncoder.encode(address1,"UTF-8")+"&"
                        +URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username1,"UTF-8");
                bufferedWriter.write(myData);
                Log.d(TAG,myData);
                bufferedWriter.flush();
                InputStream inputStream=httpURLConnection.getInputStream();
                inputStream.close();
                editor.putString("flag","update");
                editor.commit();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(task.equals("registeruser")){
            final String fullname1=params[1];
            final String username1=params[2];
            final String password1=params[3];
            final String emailid1=params[4];
            final String phone1=params[5];
            final String address1=params[6];
            final String userid1=params[7];
            Log.d(TAG, "username : " +username1);

            try {
                URL url= new URL(urlRegister);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream= httpURLConnection.getOutputStream() ;
                OutputStreamWriter outputStreamWriter= new OutputStreamWriter(outputStream, "UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData= URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(fullname1,"UTF-8")+"&"
                        +URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username1,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password1,"UTF-8")+"&"
                        +URLEncoder.encode("emailid","UTF-8")+"="+URLEncoder.encode(emailid1,"UTF-8")+"&"
                        +URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(phone1,"UTF-8")+"&"
                        +URLEncoder.encode("address","UTF-8")+"="+URLEncoder.encode(address1,"UTF-8")+"&"
                        +URLEncoder.encode("userid","UTF-8")+"="+URLEncoder.encode(userid1,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                InputStream inputStream =httpURLConnection.getInputStream();
                inputStream.close();
                editor.putString("flag","registeruser");
                editor.commit();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(task.equals("registeradmin")){
            final String fullname1=params[1];
            final String username1=params[2];
            final String password1=params[3];
            final String emailid1=params[4];
            final String phone1=params[5];
            final String address1=params[6];
            final String userid1=params[7];

            try {
                URL url= new URL(urlRegisterAdmin);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream= httpURLConnection.getOutputStream() ;
                OutputStreamWriter outputStreamWriter= new OutputStreamWriter(outputStream, "UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData= URLEncoder.encode("fullname","UTF-8")+"="+URLEncoder.encode(fullname1,"UTF-8")+"&"
                        +URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username1,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password1,"UTF-8")+"&"
                        +URLEncoder.encode("emailid","UTF-8")+"="+URLEncoder.encode(emailid1,"UTF-8")+"&"
                        +URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(phone1,"UTF-8")+"&"
                        +URLEncoder.encode("address","UTF-8")+"="+URLEncoder.encode(address1,"UTF-8")+"&"
                        +URLEncoder.encode("userid","UTF-8")+"="+URLEncoder.encode(userid1,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                InputStream inputStream =httpURLConnection.getInputStream();
                inputStream.close();
                editor.putString("flag","registeradmin");
                editor.commit();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(task.equals("userlogin")){
            final String username=params[1] ;
            String password=params[2];

            try {
                URL url= new URL(urlLogin);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream= httpURLConnection.getOutputStream() ;
                OutputStreamWriter outputStreamWriter= new OutputStreamWriter(outputStream, "UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData=URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
//                Get information from database
                InputStream inputStream =httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
                String dataresponse ="";
                StringBuilder result=new StringBuilder();
                String inputLine;
                while((inputLine=bufferedReader.readLine())!=null){
                    dataresponse+=inputLine;

                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                editor.putString("flag","userlogin");
                editor.commit();
                return (dataresponse);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if(task.equals("useradmin")){
            final String username=params[1] ;
            String password=params[2];

            try {
                URL url= new URL(urlLoginAdmin);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream= httpURLConnection.getOutputStream() ;
                OutputStreamWriter outputStreamWriter= new OutputStreamWriter(outputStream, "UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData=URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
//                Get information from database
                InputStream inputStream =httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
                String dataresponse ="";
                StringBuilder result=new StringBuilder();
                String inputLine;
                while((inputLine=bufferedReader.readLine())!=null){
                    dataresponse+=inputLine;

                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                editor.putString("flag","useradmin");
                editor.commit();
                return (dataresponse);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {

        String flag = preferences.getString("flag","0");
        if(flag.equals("adminenergybreakdown")){
            String[] serverResponse = s.split("[,]");
            arraylist1= new ArrayList<>();

            arraylist1=Arrays.asList(serverResponse);
            List<String> arraylist2=arraylist1.subList(0,5);
            List<String> arraylist3 = arraylist1.subList(5,10);
//            UserPiechart pie= new UserPiechart();
//            pie.getArrayList(arraylist2,arraylist3);
            editor.putString("arraylist2", arraylist2.toString());
            editor.commit();
            editor.putString("arraylist3", arraylist3.toString());
            editor.commit();

            Intent intent = new Intent(context, UserPiechart.class);
            context.startActivity(intent);
        }
        if (flag.equals("adminuserdetails")){

            String[] serverResponse = s.split("[,]");
            arraylist=  new ArrayList<>();
            arraylist= Arrays.asList(serverResponse);
//            AdminUserDetails admin = new AdminUserDetails();
//            admin.getArrayList(arraylist);
        }
        if(flag.equals("update")){
            Toast.makeText(context,"Updated Successfully Login again to view new data",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, Login.class);
            context.startActivity(intent);
        }
        if(flag.equals("registeruser")) {
            Log.d(TAG, "registeruser: " + s);
            Toast.makeText(context,"Registered Successfully",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, Login.class);
            context.startActivity(intent);
        }
        if(flag.equals("registeradmin")) {
            Toast.makeText(context,"Registered Successfully",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, Login.class);
            context.startActivity(intent);
        }
        if(flag.equals("userlogin")) {
            String test = "false";
            String userid = "";
            String username;
            String password;
            String emailid;
            String phone;
            String address;
            String houseID;

            String[] serverResponse = s.split("[,]");

            Log.d(TAG,"test:"+s);
            test = serverResponse[0];
            userid = serverResponse[1];
            if (test.equals("true")) {
                username = serverResponse[2];
                password = serverResponse[3];
                emailid = serverResponse[4];
                phone = serverResponse[5];
                address = serverResponse[6];
                houseID = serverResponse[7];
                Log.d(TAG, "houseid: " + houseID);
                editor.putString("username", username);
                editor.commit();
                editor.putString("password", password);
                editor.commit();
                editor.putString("emailid", emailid);
                editor.commit();
                editor.putString("phone", phone);
                editor.commit();
                editor.putString("address", address);
                editor.commit();
                editor.putString("houseID", houseID);
                editor.commit();
                Intent intent = new Intent(context, MainActivityUser.class);
                context.startActivity(intent);


            } else
                Toast.makeText(context, "Login Not successful", Toast.LENGTH_LONG).show();
        }
            if(flag.equals("useradmin")) {
                String test1 = "";
                String userid1 = "";
                String[] serverResponse1 = s.split("[,]");
                test1 = serverResponse1[0];
                userid1 = serverResponse1[1];
                Log.d(TAG, serverResponse1[0]);
                if (test1.equals("true")) {
                    Intent intent = new Intent(context, MainActivityAdmin.class);
                    context.startActivity(intent);

                } else
                    Toast.makeText(context, "Login Not successful", Toast.LENGTH_LONG).show();
            }
        }



    @Override
    protected void onProgressUpdate(Void... values) {

        super.onProgressUpdate(values);
    }

}
