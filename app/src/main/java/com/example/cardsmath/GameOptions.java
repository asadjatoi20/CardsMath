package com.example.cardsmath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class GameOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // to hide the top screen
        setContentView(R.layout.activity_game_options);
    }
    public void helpBtn(View v){
        // play Intent Activity
        Intent intent = new Intent(GameOptions.this, Help.class);
        startActivity(intent);
    }

    public void playBtn(View v){
        Intent intent = new Intent(GameOptions.this, Game.class);
        startActivity(intent);
    }
}