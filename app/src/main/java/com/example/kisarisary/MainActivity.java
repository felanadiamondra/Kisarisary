package com.example.kisarisary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.kisarisary.view.DrawingView;

public class MainActivity extends AppCompatActivity {

    private DrawingView drawingView;
    private Color color;
    private int red = 0;
    private int blue = 0;
    private int green = 0;
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
                cl.setBackgroundColor(Color.WHITE);
                break;
            case 2:
                cl.setBackgroundResource(R.drawable.colorfulwood);
                break;
            default:
                cl.setBackgroundResource(R.drawable.grass);
                break;

        }
    }

    public void setUpToolbarEventListener(){
        ImageButton btnDrawLine = (ImageButton) findViewById(R.id.btnDrawLine);
        ImageButton btnDrawCircle = (ImageButton) findViewById(R.id.btnDrawCircle);
        ImageButton btnDrawRect = (ImageButton) findViewById(R.id.btnDrawRect);
        ImageButton btnUndo = (ImageButton) findViewById(R.id.btnUndo);
        ImageButton btnPaint = (ImageButton) findViewById(R.id.btnPaint);
        SeekBar seekbar =  (SeekBar) findViewById(R.id.penSize);
        TextView penSizeValue = (TextView) findViewById(R.id.penSizeValue);

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

        btnDrawLine.setOnClickListener(v -> drawingView.setCurrentDrawingType(
                DrawingView.DRAWING_TYPE_LINE));

        btnUndo.setOnClickListener(v -> drawingView.undo());

        btnPaint.setOnClickListener(v -> drawingView.setPainting());

    }

    public void btnShowColorDialog(View viewSource){
        red = 0;
        green = 0;
        blue = 0;
        final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        View mview = getLayoutInflater().inflate(R.layout.custom_color, null);
        alert.setView(mview);
        final AlertDialog alertDialog= alert.create();
        alertDialog.setCanceledOnTouchOutside(true);
        SeekBar seekBarRed = (SeekBar) mview.findViewById(R.id.seekBarRed);
        SeekBar seekBarGreen = (SeekBar) mview.findViewById(R.id.seekBarGreen);
        SeekBar seekBarBlue = (SeekBar) mview.findViewById(R.id.seekBarBlue);
        View viewColor = (View) mview.findViewById(R.id.viewColor);
        Button button = (Button) mview.findViewById(R.id.buttonColor);
        // alertDialog.dismiss();
        alertDialog.show();
        viewColor.setBackgroundColor(Color.rgb(red, green, blue));
        seekBarRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                red = progress;
                viewColor.setBackgroundColor(Color.rgb(red, green, blue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                green = progress;
                viewColor.setBackgroundColor(Color.rgb(red, green, blue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBarBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blue = progress;
                viewColor.setBackgroundColor(Color.rgb(red, green, blue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button.setOnClickListener(v ->{
            drawingView.setCurrentColor(Color.rgb(red, green, blue));
            alertDialog.dismiss();
        });
    }
}