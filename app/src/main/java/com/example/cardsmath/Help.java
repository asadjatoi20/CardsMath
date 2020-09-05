package com.example.cardsmath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // to hide the top screen
        setContentView(R.layout.activity_help);
    }
    public void backBtn(View v){
        Intent intent = new Intent(Help.this, GameOptions.class);
        startActivity(intent);
        finish();
    }
}