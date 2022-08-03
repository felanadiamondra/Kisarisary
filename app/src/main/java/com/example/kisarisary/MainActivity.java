package com.example.kisarisary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

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
        ImageButton btnDrawRect = (ImageButton) findViewById(R.id.btnDrawRect);
        ImageButton btnUndo = (ImageButton) findViewById(R.id.btnUndo);
        SeekBar seekbar =  (SeekBar) findViewById(R.id.penSize);
        TextView penSizeValue = (TextView) findViewById(R.id.penSizeValue);
        TextView text = (TextView) findViewById(R.id.textHere);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                penSizeValue.setText(progress+"dp");
                drawingView.setCurrentStrokeWidth(progress);
                seekbar.setMax(50);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnDrawRect.setOnClickListener(v -> drawingView.setCurrentDrawingType(
                DrawingView.DRAWING_TYPE_RECT));

        btnDrawCircle.setOnClickListener(v -> drawingView.setCurrentDrawingType(
                DrawingView.DRAWING_TYPE_ELLIPSE));

        btnUndo.setOnClickListener(v -> drawingView.undo());



    }

    public void btnShowColorDialog(View viewSource){
        final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        View mview = getLayoutInflater().inflate(R.layout.custom_color, null);
        alert.setView(mview);
        final AlertDialog alertDialog= alert.create();
        alertDialog.setCanceledOnTouchOutside(true);
        // alertDialog.dismiss();
        alertDialog.show();
    }

}