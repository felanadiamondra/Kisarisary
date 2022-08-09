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
public class DrawingView extends View{
    private static final float touch = 4;
    private float mX, mY;
    private Path mPath;
    Paint paint = new Paint();
    private int currentColor;
    private int strokeWidth;
    private int drawingType;
    private boolean isPaintActive = false;
    public DrawMethod drawMeth;
    ArrayList<Shape> shapeTmp = new ArrayList<Shape>();
    ArrayList<Shape> shapes = new ArrayList<Shape>();

    int startX = -1;
    int startY = -1;
    int endX = -1;
    int endY= -1;
    int touchPaintX = -1;
    int touchPaintY = -1;

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
        initializePaint();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(startX !=0 && startY !=0){
            if(shapeTmp.size() != 0){
                paint.setColor(shapeTmp.get(0).getStrokeColor());
                paint.setStrokeWidth(shapeTmp.get(0).getStrokeWidth());
                drawTmpShape(shapeTmp, canvas);
            }

            for(int i=0; i< shapes.size(); i++){
                switch (shapes.get(i).getType()){
                    case 1:
                        drawMeth = new DrawMethod(new DrawRect());
                        break;
                    case 2:
                        drawMeth = new DrawMethod(new DrawEllipse());
                        break;
                    case 3:
                        drawMeth = new DrawMethod(new DrawPath());
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
        Shape shape = shapeTmp.get(0);
        TouchCoordinates touchC = shape.getTouchC();
        switch (shape.getType()){
            case 1:
                drawMeth = new DrawMethod(new DrawRect());
                break;
            case 2:
                drawMeth = new DrawMethod(new DrawEllipse());
                break;
            case 3 :
                drawMeth = new DrawMethod(new DrawPath());
                break;
        }
        drawMeth.drawPaintImageTmp(startX, startY, endX , endY, paint, canvas);
        // canvas.drawRect(startX, startY, endX , endY, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            System.out.println(isPaintActive);
            if(isPaintActive){
                touchPaintX = (int) event.getX();
                touchPaintY = (int) event.getY();
                System.out.println("Touch X : " + touchPaintX);
                System.out.println("Touch Y : " + touchPaintY);
                paintShapeTouched();
                invalidate();
            }
            else{
                startX = (int)event.getX();
                startY = (int)event.getY();
            }

        }
        if(event.getAction() == MotionEvent.ACTION_MOVE)
        {
            if(!isPaintActive){
                endX = (int)event.getX();
                endY = (int)event.getY();
                TouchCoordinates touchC = new TouchCoordinates(startX, startY, endX, endY);
                Shape shape = new Shape(touchC , 0, currentColor, strokeWidth, drawingType);
                shapeTmp.add(shape);
                invalidate();
            }
        }
        if (event.getAction() == MotionEvent.ACTION_UP)
        {
            if(!isPaintActive){
                shapeTmp.clear();
                TouchCoordinates tc= new TouchCoordinates(startX, endX, startY, endY);
                Shape shape = new Shape(tc, 0, currentColor, strokeWidth, drawingType);
                shapes.add(shape);
                initializePaint();
                invalidate();
            }
        }
        return true;
    }

    public void paintShapeTouched(){
        System.out.println("Touching is active now");
        for(int i=shapes.size()-1; i >= 0; i--){
            TouchCoordinates tc = shapes.get(i).getTouchC();

            if( touchPaintX <= tc.rightTop
                    && touchPaintX >= tc.leftTop
                    && touchPaintY <= tc.rightBottom
                    && touchPaintY >= tc.leftBottom){
                System.out.println("Yes touched");
                shapes.get(i).setColor(currentColor);
            }

        }
    }

    public void initializePaint(){
        paint.setColor(currentColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(strokeWidth);
    }

    public void setCurrentDrawingType(int dt){
        this.drawingType = dt;
    }

    public void setCurrentStrokeWidth(int sw){
        this.strokeWidth = sw;
    }

    public void setCurrentColor(int color){
        this.currentColor = color;
    }

    public void setPainting(){
        this.isPaintActive = true;
    }
}
