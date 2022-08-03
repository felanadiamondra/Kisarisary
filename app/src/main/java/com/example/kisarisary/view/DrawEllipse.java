package com.example.kisarisary.view;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

public class DrawEllipse implements DrawShape{
    @Override
    public void drawPaintImage(Canvas canvas, Shape shape, Paint paint) {
        int rectWidth = shape.getStrokeWidth();
        TouchCoordinates tc = shape.getTouchC();
        paint.setStrokeWidth(rectWidth);
        canvas.drawOval(tc.leftTop, tc.leftBottom, tc.rightTop, tc.rightBottom, paint);
    }
    @Override
    public void drawImageTemp(Canvas canvas, ArrayList<Shape> shape) {

    }
}
