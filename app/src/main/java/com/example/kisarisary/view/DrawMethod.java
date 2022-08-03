package com.example.kisarisary.view;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

public class DrawMethod {
    private DrawShape drawShape;

    public DrawMethod(DrawShape drawShape){
        this.drawShape = drawShape;
    }

    public void drawPaintImage(Canvas canvas, Shape shape, Paint paint){
        drawShape.drawPaintImage(canvas, shape, paint);
    }
    public void drawPaintImageTmp(int startX, int startY, int endX, int endY, Paint paint, Canvas canvas){
        drawShape.drawPaintImageTmp(startX, startY, endX, endY, paint, canvas);
    }
}
