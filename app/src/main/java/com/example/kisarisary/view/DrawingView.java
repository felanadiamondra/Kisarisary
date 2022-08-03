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

enum colorPalette{
    GREEN,
    RED,
    BLUE,
    DARK,
    CYAN,
    MAGENTA,
    YELLOW,
    GRAY,
    DKGRAY,
    LTGRAY
}

public class DrawingView extends View{
    private static final float touch = 4;
    private float mX, mY;
    private Path mPath;
    private ArrayList<Stroke> paths = new ArrayList<>();
    private int currentColor;
    private int strokeWidth;
    private Bitmap mBitmap;
    Paint paint = new Paint();
    private int drawingType;
    public DrawMethod drawMeth;

    ArrayList<TouchCoordinates> touchC = new ArrayList<TouchCoordinates>();
    ArrayList<Shape> shapeTmp = new ArrayList<Shape>();
    ArrayList<Shape> shapes = new ArrayList<Shape>();

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
        drawMeth = new DrawMethod(new DrawRect());
        currentColor = Color.GREEN;
        strokeWidth  = 5;
        drawingType = 1;
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(strokeWidth);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(startX !=0 && startY !=0){
            drawTmpShape(shapeTmp, canvas);
            for(int i=0; i< shapes.size(); i++){
                switch (shapes.get(i).getType()){
                    case 1:
                        drawMeth = new DrawMethod(new DrawRect());
                        break;
                    case 2:
                        drawMeth = new DrawMethod(new DrawEllipse());
                        break;
                    default:
                        break;
                }
                drawMeth.drawPaintImage(canvas, shapes.get(i), paint);
            }
        }
    }

    public void undo(){
        if(shapes.size() !=0){
            shapes.remove(shapes.size() - 1);
            invalidate();
        }
    }
    protected void drawTmpShape(ArrayList<Shape> shapeTmp, Canvas canvas){
        if(shapeTmp.size() != 0){
            drawMeth.drawPaintImageTmp(canvas, shapeTmp);
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
            /*if(drawingType !=3){
                TouchCoordinates touchC = new TouchCoordinates(startX, startY, endX, endY, drawingType);
                endX = (int)event.getX();
                endY = (int)event.getY();
                touchCtmp.add(touchC);
            }*/
            endX = (int)event.getX();
            endY = (int)event.getY();
            TouchCoordinates touchC = new TouchCoordinates(startX, startY, endX, endY);
            Shape shape = new Shape(touchC , currentColor, strokeWidth, drawingType);
            shapeTmp.add(shape);
            invalidate();
        }
        if (event.getAction() == MotionEvent.ACTION_UP)
        {
            shapeTmp.clear();
            TouchCoordinates tc= new TouchCoordinates(startX, endX, startY, endY);
            Shape shape = new Shape(tc, currentColor, strokeWidth, drawingType);
            shapes.add(shape);
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
