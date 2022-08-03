package com.example.kisarisary.view;

import android.graphics.Canvas;

import java.util.ArrayList;

public class DrawMethod {
    private DrawShape drawShape;

    public DrawMethod(DrawShape drawShape){
        this.drawShape = drawShape;
    }

    /*public void drawPaintImage(Canvas canvas, ArrayList<Shape> shape){
        drawShape.drawPaintImage(canvas, shape);
    } */
    public void drawPaintImageTmp(Canvas canvas, ArrayList<Shape> shape){
        drawShape.drawImageTemp(canvas, shape);
    }
}
