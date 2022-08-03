package com.example.kisarisary.view;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

public interface DrawShape {
    public void drawPaintImage(Canvas canvas, Shape shape, Paint paint);
    public void drawPaintImageTmp(int startX, int startY, int endX, int endY, Paint paint, Canvas canvas);
}
