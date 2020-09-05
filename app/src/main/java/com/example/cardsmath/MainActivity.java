package com.example.cardsmath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 4200;
    MediaPlayer mediaPlayer;
    Animation top,btm;
    ImageView imView;
    TextView title,slogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // to hide the top screen
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.music);
        mediaPlayer.setLooping(true); // Set looping
        mediaPlayer.setVolume(100, 100);
        mediaPlayer.start();

        imView = findViewById(R.id.splash);
        title = findViewById(R.id.title);
        slogan = findViewById(R.id.slogan);

        top = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        btm = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        imView.setAnimation(top);
        title.setAnimation(btm);
        slogan.setAnimation(btm);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, GameOptions.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);

    }
}