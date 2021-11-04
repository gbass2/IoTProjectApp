package com.example.a49ersense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.ivy.util.url.ApacheURLLister;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class VideoGalleryList extends AppCompatActivity {

    URL url1;
    List serverDir;
    ListView galleryList;
    public final String TAG="data.dot";
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_gallery_list);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        findViews();
    }

    protected void findViews() {
        galleryList = findViewById(R.id.gallerylist);
        serverDir=new ArrayList();
        List arraylist=GetfromServer();
        Log.d(TAG,arraylist.toString());

        final ArrayAdapter arrayAdapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1,arraylist);
        galleryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>array, View view, int position, long id) {
                String value = (String)array.getItemAtPosition(position);
                Intent intent = new Intent(Intent.ACTION_VIEW);

                intent.setDataAndType(Uri.parse(value), "video/*");

                startActivity(Intent.createChooser(intent, "Complete action using"));

//                preferences=context.getSharedPreferences("MYPREFS",Context.MODE_PRIVATE);
//                editor = preferences.edit();
//                editor.putString("videoname",value);
//                editor.commit();
            }
        });
        galleryList.setAdapter(arrayAdapter);

    }
    public List GetfromServer(){
        try {
            url1=new URL("http://192.168.1.226/49ersense/videos/");
            ApacheURLLister lister = new ApacheURLLister();
            serverDir.addAll(lister.listAll(url1));

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return serverDir;

    }

}




