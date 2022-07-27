package com.example.kisarisary.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;

import androidx.annotation.Nullable;

import java.util.ArrayList;

class TouchCoordinates {
    int leftTop;
    int rightTop;
    int leftBottom;
    int rightBottom;

    TouchCoordinates(int lt, int rt, int lb, int rb){
        this.leftTop = lt;
        this.rightTop = rt;
        this.leftBottom = lb;
        this.rightBottom = rb;
    }


}

public class DrawingView extends View {
    private static final float touch = 4;
    private float mX, mY;
    private Path mPath;
    private ArrayList<Stroke> paths = new ArrayList<>();
    private int currentColor;
    private int strokeWidth;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mBitmapPaint = new Paint(Paint.DITHER_FLAG);
    Paint paint = new Paint();

    ArrayList<TouchCoordinates> touchC = new ArrayList<TouchCoordinates>();

    int startX = -1;
    int startY = -1;
    int endX = -1;
    int endY= -1;
    public DrawingView(Context context) {
        super(context);
    }

    public DrawingView(
            Context context,
            @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    public void init(int width, int height){
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        // initial color
        currentColor = Color.GREEN;
        // initial size
        strokeWidth = 20;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawRectTemp(canvas);
        if(startX !=0 && startY !=0){
            for(int i=0; i< touchC.size(); i++){
                paint.setColor(Color.rgb(i*15, i*150, i*84));
                canvas.drawOval(touchC.get(i).leftTop, touchC.get(i).leftBottom, touchC.get(i).rightTop, touchC.get(i).rightBottom, paint);
            }
        }
    }

    protected void drawRectTemp(Canvas canvas){
        canvas.drawOval(startX, startY, endX, endY, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            startX = (int)event.getX();
            startY = (int)event.getY();
        }
        if(event.getAction() == MotionEvent.ACTION_MOVE)
        {
            endX = (int)event.getX();
            endY = (int)event.getY();

            invalidate();
        }
        if (event.getAction() == MotionEvent.ACTION_UP)
        {
            TouchCoordinates tc= new TouchCoordinates(startX, endX, startY, endY);
            touchC.add(tc);
            invalidate();
        }



        return true;
    }
}
