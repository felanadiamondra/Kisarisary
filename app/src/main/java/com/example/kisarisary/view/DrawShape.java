package com.example.kisarisary.view;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

public interface DrawShape {
    public void drawPaintImage(Canvas canvas, Shape shape, Paint paint);
    public void drawImageTemp(Canvas canvas, ArrayList<Shape> shape);
}
