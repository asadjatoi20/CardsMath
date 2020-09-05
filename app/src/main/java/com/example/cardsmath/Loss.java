package com.example.cardsmath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class Loss extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // to hide the top screen
        setContentView(R.layout.activity_loss);
        mediaPlayer = MediaPlayer.create(this, R.raw.laugh);
        mediaPlayer.setLooping(true); // Set looping
        mediaPlayer.setVolume(100, 100);
        mediaPlayer.start();

    }
    public void homeBtn(View v){
        mediaPlayer.stop();
        mediaPlayer.release();
        Intent intent = new Intent(Loss.this, GameOptions.class);
        startActivity(intent);
        finish();
    }
}