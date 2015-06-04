package com.brian.whiteboardtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Stack;

public class WhiteBoardView extends View implements View.OnTouchListener {

    protected Path latestPath = null;
    protected Stack<Path> paths = new Stack<Path>();
    private Paint currentPaint = new Paint();
    private boolean penDown = false;

    public WhiteBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.setOnTouchListener(this);

        currentPaint.setColor(Color.BLUE);
        currentPaint.setStrokeWidth(20);
        currentPaint.setStyle(Paint.Style.STROKE);
        currentPaint.setStrokeJoin(Paint.Join.ROUND);
        setDrawingCacheEnabled(true);

    }


    @Override
    public void onDraw(Canvas canvas) {
        if (latestPath!=null) canvas.drawPath(latestPath, currentPaint);
        for (Path path : paths) {
            canvas.drawPath(path, currentPaint);
        }

    }

    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (latestPath!=null)  paths.push(latestPath);
                penDown = true;
                latestPath = new Path();
                latestPath.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
               /* if (penDown) {
                    penDown = false;
                    paths.push(latestPath);
                    latestPath=null;
                }
*/
                break;
            case MotionEvent.ACTION_MOVE:
               if (penDown) latestPath.lineTo(event.getX(), event.getY());
               break;

            default:
                return false;
        }
        invalidate();


        return true;
    }


    public void  setPenColor (int cl) {
        currentPaint.setColor(cl);
    }



    public void erase()
    {
        if (!paths.empty())
        {

            paths.clear();


        }
        latestPath = null;
        invalidate();
    }

    public void undo()
    {
        if (!paths.empty())
        {
            latestPath = paths.pop();


        }
       else  latestPath=null;
        invalidate();
    }

    public void redo() {
        if (latestPath != null) {
            paths.push(latestPath);
            latestPath = null;
            invalidate();

        }

    }

    public void captureAndSend() {

        try {

            getDrawingCache().compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(new File(Environment.getExternalStorageDirectory(),"screencap.jpg")));

        } catch (Exception e) {
            Log.e("Error--------->", e.toString());
        }
    }
}