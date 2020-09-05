package com.example.cardsmath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Win extends AppCompatActivity {
    TextView viewAnim;
    MediaPlayer mediaPlayer;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // to hide the top screen
        setContentView(R.layout.activity_win);
        viewAnim = findViewById(R.id.youwin);

        mediaPlayer = MediaPlayer.create(this, R.raw.clap);
        mediaPlayer.setLooping(true); // Set looping
        mediaPlayer.setVolume(100, 100);
        mediaPlayer.start();

        animation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        viewAnim.setAnimation(animation);

    }
    public void homeBtn(View v){
        mediaPlayer.stop();
        mediaPlayer.release();
        Intent intent = new Intent(Win.this,GameOptions.class);
        startActivity(intent);
        finish();
    }
}