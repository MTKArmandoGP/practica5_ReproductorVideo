package com.example.reproductortest2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int codigo_video = 1;

    private Button selectVideoButton;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectVideoButton = findViewById(R.id.button_select_video);
        videoView = findViewById(R.id.video_view);

        selectVideoButton.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, codigo_video);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        VideoView video=findViewById(R.id.video_view);
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == codigo_video && resultCode == RESULT_OK && data != null) {
            Uri videoUri = data.getData();

            if (videoUri != null) {
                videoView.setVideoURI(videoUri);
                videoView.start();
                MediaController mediaController=new MediaController(this);
                video .setMediaController(mediaController);
                mediaController.setAnchorView(video);
            } else {
                Toast.makeText(this, "Failed to load video", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

