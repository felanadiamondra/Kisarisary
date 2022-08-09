package com.example.kisarisary.view;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

public class DrawRect implements DrawShape{
    @Override
    public void drawPaintImage(Canvas canvas, Shape shape, Paint paint) {
        int rectWidth = shape.getStrokeWidth();
        TouchCoordinates tc = shape.getTouchC();
        paint.setStrokeWidth(rectWidth);
        paint.setColor(shape.getStrokeColor());
       // paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(tc.leftTop, tc.leftBottom, tc.rightTop, tc.rightBottom, paint);
        if(shape.getColor() != 0){
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(shape.getColor());
            canvas.drawRect(tc.leftTop, tc.leftBottom, tc.rightTop, tc.rightBottom, paint);
        }
    }

    @Override
    public void drawPaintImageTmp(int startX, int startY, int endX, int endY, Paint paint, Canvas canvas) {
        canvas.drawRect(startX, startY, endX , endY, paint);
    }
}
