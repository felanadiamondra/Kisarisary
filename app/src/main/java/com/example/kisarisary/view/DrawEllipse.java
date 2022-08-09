package com.example.kisarisary.view;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

public class DrawEllipse implements DrawShape{
    @Override
    public void drawPaintImage(Canvas canvas, Shape shape, Paint paint) {
        int ovalWidth = shape.getStrokeWidth();
        TouchCoordinates tc = shape.getTouchC();
        paint.setStrokeWidth(ovalWidth);
        paint.setColor(shape.getStrokeColor());
        canvas.drawOval(tc.leftTop, tc.leftBottom, tc.rightTop, tc.rightBottom, paint);
        if(shape.getColor() != 0){
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(shape.getColor());
            canvas.drawOval(tc.leftTop, tc.leftBottom, tc.rightTop, tc.rightBottom, paint);
        }
    }

    @Override
    public void drawPaintImageTmp(int startX, int startY, int endX, int endY, Paint paint, Canvas canvas) {
        canvas.drawOval(startX, startY, endX , endY, paint);
    }
}
