package com.example.kisarisary.view;

import android.graphics.Color;

public class Shape {
    private int type;
    private TouchCoordinates touchC;
    private int color;
    private int strokeWidth;

    public Shape(TouchCoordinates touchC, int color, int stroke,
    int type){
        this.touchC = touchC;
        this.color = color;
        this.strokeWidth = stroke;
        this.type = type;
    }

    public TouchCoordinates getTouchC(){
        return this.touchC;
    }

    public int getColor(){
        return this.color;
    }

    public int getStrokeWidth(){
        return this.strokeWidth;
    }

    public int getType(){
        return this.type;
    }

}
