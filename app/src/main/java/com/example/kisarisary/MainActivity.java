package com.example.kisarisary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.kisarisary.view.DrawingView;

public class MainActivity extends AppCompatActivity {

    private DrawingView drawingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawingView = (DrawingView) findViewById(R.id.drawingView);
        setBackgroundImage();
        setUpToolbarEventListener();
    }

    public void setBackgroundImage(){
        int bgImage = getIntent().getIntExtra("bgImage", 0);

        ConstraintLayout cl= (ConstraintLayout) findViewById(R.id.launchMain);
        switch (bgImage){
            case 1:
                cl.setBackgroundResource(R.drawable.colorfulwood);
                break;
            default:
                cl.setBackgroundColor(Color.RED);
        }
    }

    public void setUpToolbarEventListener(){
        ImageButton btnDrawLine = (ImageButton) findViewById(R.id.btnDrawLine);
        ImageButton btnDrawCircle = (ImageButton) findViewById(R.id.btnDrawCircle);
        ImageButton btnUndo = (ImageButton) findViewById(R.id.btnUndo);

        btnDrawLine.setOnClickListener(v -> drawingView.setCurrentDrawingType(
                DrawingView.DRAWING_TYPE_RECT));

        btnDrawCircle.setOnClickListener(v -> drawingView.setCurrentDrawingType(
                DrawingView.DRAWING_TYPE_ELLIPSE));

        btnUndo.setOnClickListener(v -> drawingView.undo());
    }

}