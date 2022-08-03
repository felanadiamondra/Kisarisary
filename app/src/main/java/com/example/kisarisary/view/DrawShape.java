package com.example.kisarisary.view;

import android.graphics.Canvas;

import java.util.ArrayList;

public interface DrawShape {
    public void drawPaintImage(Canvas canvas, Shape shape);
    public void drawImageTemp(Canvas canvas, ArrayList<Shape> shape);
}
