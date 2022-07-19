package com.example.kisarisary.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
    Paint paint = new Paint();
    int colorBg = 0;
    ArrayList<Integer> tabX = new ArrayList<Integer>();
    ArrayList<Integer> tabY = new ArrayList<Integer>();
    ArrayList<TouchCoordinates> touchC = new ArrayList<TouchCoordinates>();

    int touchX = -1;
    int touchY = -1;
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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(startX !=0 && startY !=0){
            for(int i=0; i< touchC.size(); i++){
                paint.setColor(Color.rgb(i*15, i*150, i*84));
                canvas.drawOval(touchC.get(i).leftTop, touchC.get(i).leftBottom, touchC.get(i).rightTop, touchC.get(i).rightBottom, paint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

       /* touchX = (int)event.getX();
        touchY = (int)event.getY();*/

        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            startX = (int)event.getX();
            startY = (int)event.getY();
        }
        if (event.getAction() == MotionEvent.ACTION_UP)
        {
            //tabX.add(touchX);
            //tabY.add(touchY);
            endX = (int)event.getX();
            endY = (int)event.getY();
            TouchCoordinates tc= new TouchCoordinates(startX, endX, startY, endY);
            touchC.add(tc);
            //startX = -1;
            //startY = -1;
        }

        invalidate();

        return true;
    }
}
