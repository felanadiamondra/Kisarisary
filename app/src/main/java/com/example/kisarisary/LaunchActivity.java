package com.example.kisarisary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LaunchActivity extends AppCompatActivity {
    private int bgImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
    }

    public void launchPaint(View viewSource){
        //drawingView.putExtra("bgImage",  1);
        Intent drawingView = new Intent(this, MainActivity.class);
        drawingView.putExtra("bgImage", bgImage);
        startActivity(drawingView);
    }

    public void setDefaultBackground(View viewSource){
        this.bgImage = 1;
    }

    public void setWoodBackground(View viewSource){
        this.bgImage =2;
    }

    public void setGrassBackground(View viewSource){
        this.bgImage = 3;
    }
}