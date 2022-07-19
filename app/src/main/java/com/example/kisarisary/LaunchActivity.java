package com.example.kisarisary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
    }

    public void launchPaint(View viewSource){
        Intent drawingView = new Intent(this, MainActivity.class);
        startActivity(drawingView);
    }
}