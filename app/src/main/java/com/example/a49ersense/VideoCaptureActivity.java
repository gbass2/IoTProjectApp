package com.example.a49ersense;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class VideoCaptureActivity extends AppCompatActivity {
    WebView webView;
    Button startVideoCapture;
    Button videoGallery;
    String streamURL="http://192.168.137.32:8081";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_capture);

        findViews();
    }

    protected void findViews() {
        webView=findViewById(R.id.webView);
        startVideoCapture = findViewById(R.id.button7);
        videoGallery=findViewById(R.id.video);

        setUpButtonListeners();
    }



    protected void setUpButtonListeners() {
        startVideoCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl(streamURL);
                startVideoCapture.setVisibility(View.INVISIBLE);
                videoGallery.setVisibility(View.INVISIBLE);
            }
        });
        videoGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(VideoCaptureActivity.this,VideoGalleryList.class);
                startActivity(homeIntent);

            }
        });

    }

}
