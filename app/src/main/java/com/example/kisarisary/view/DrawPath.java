package com.example.kisarisary.view;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

public class DrawPath implements DrawShape{
    @Override
    public void drawPaintImage(Canvas canvas, Shape shape, Paint paint) {
        int lineWidth = shape.getStrokeWidth();
        TouchCoordinates tc = shape.getTouchC();
        paint.setStrokeWidth(lineWidth);
        paint.setColor(shape.getColor());
        canvas.drawLine(tc.leftTop, tc.leftBottom, tc.rightTop, tc.rightBottom, paint);
    }

    @Override
    public void drawPaintImageTmp(int startX, int startY, int endX, int endY, Paint paint, Canvas canvas) {
        canvas.drawLine(startX, startY, endX , endY, paint);
    }

}
