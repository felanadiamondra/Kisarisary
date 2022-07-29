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
    int type;

    TouchCoordinates(int lt, int rt, int lb, int rb, int type){
        this.leftTop = lt;
        this.rightTop = rt;
        this.leftBottom = lb;
        this.rightBottom = rb;
        this.type = type;
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
    Paint paint = new Paint();
    private int drawingType;

    ArrayList<TouchCoordinates> touchC = new ArrayList<TouchCoordinates>();
    ArrayList<TouchCoordinates> touchCtmp = new ArrayList<TouchCoordinates>();

    int startX = -1;
    int startY = -1;
    int endX = -1;
    int endY= -1;

    public static final int DRAWING_TYPE_RECT=1;
    public static final int DRAWING_TYPE_ELLIPSE=2;
    public static final int DRAWING_TYPE_LINE=3;

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
        // initial color
        currentColor = Color.GREEN;
        // initial size
        strokeWidth = 20;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStrokeWidth(strokeWidth);
        if(startX !=0 && startY !=0){
            if(touchCtmp.size() != 0){
                canvas.drawOval(startX, startY, endX, endY, paint);
            }
            for(int i=0; i< touchC.size(); i++){
                switch (touchC.get(i).type){
                    case 1:
                        paint.setColor(Color.rgb(i*15, i*150, i*84));
                        canvas.drawRect(touchC.get(i).leftTop, touchC.get(i).leftBottom, touchC.get(i).rightTop, touchC.get(i).rightBottom, paint);
                        break;
                    case 2:
                        paint.setColor(Color.rgb(i*15, i*150, i*84));
                        canvas.drawOval(touchC.get(i).leftTop, touchC.get(i).leftBottom, touchC.get(i).rightTop, touchC.get(i).rightBottom, paint);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void undo(){
        if(touchC.size() !=0){
            touchC.remove(touchC.size() - 1);
            invalidate();
        }
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
            TouchCoordinates touchC = new TouchCoordinates(startX, startY, endX, endY, drawingType);
            endX = (int)event.getX();
            endY = (int)event.getY();
            touchCtmp.add(touchC);

            invalidate();
        }
        if (event.getAction() == MotionEvent.ACTION_UP)
        {
            touchCtmp.clear();
            TouchCoordinates tc= new TouchCoordinates(startX, endX, startY, endY, drawingType);
            touchC.add(tc);
            invalidate();
        }
        return true;
    }

    public void setCurrentDrawingType(int dt){
        this.drawingType = dt;
    }

    public void setCurrentStrokeWidth(int sw){
        this.strokeWidth = sw;
    }
}
