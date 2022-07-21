package com.example.kisarisary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBackgroundImage();
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

}