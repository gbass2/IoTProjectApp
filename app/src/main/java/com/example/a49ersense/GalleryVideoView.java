package com.example.a49ersense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.VideoView;

public class GalleryVideoView extends AppCompatActivity {
VideoView Galleryview;
SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_video_view);
        findViews();
        String mVideoURL= preferences.getString("videoname","");
        String videoURL= mVideoURL;
    }
    protected void findViews() {
        Galleryview= findViewById(R.id.videoView1);
    }
}
