package com.example.a49ersense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;
import android.widget.VideoView;

public class GalleryVideoView extends AppCompatActivity {
//VideoView Galleryview;
VideoView videov;
String videoURL;
SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        Intent intent = getIntent();
        videoURL = intent.getStringExtra("url");
        videov = findViewById(R.id.videoView1);
        videov.setVideoPath(videoURL);
        videov.start();
    }
}
