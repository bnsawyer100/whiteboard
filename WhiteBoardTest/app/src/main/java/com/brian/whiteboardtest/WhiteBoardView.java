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
import java.util.ArrayList;

public class WhiteBoardView extends View implements View.OnTouchListener {

    private Pathpart latestPath = null;
    private ArrayList<Pathpart> paths = new ArrayList<Pathpart>();
    private Paint currentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private boolean penDown = false;
    private int currentIndex = 0;

    public class Pathpart {
        private Path thepath;
        private Paint thepaint;
    }

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
        int max = currentIndex;


        if (paths.size()>0)
             for (int i=0; i<max;++i) {

                canvas.drawPath(paths.get(i).thepath, paths.get(i).thepaint);
            }

    }

    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                penDown = true;
                latestPath = new Pathpart();
                latestPath.thepath = new Path();
                latestPath.thepaint= currentPaint;
                int size = paths.size();
                if (size>currentIndex) {
                    for (int i1=currentIndex;i1<size;++i1)
                         paths.remove(paths.size() - 1);
                }
                paths.add(latestPath);
                ++currentIndex;
                latestPath.thepath.moveTo(event.getX(), event.getY());
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
               if (penDown) latestPath.thepath.lineTo(event.getX(), event.getY());
               break;

            default:
                return false;
        }
        invalidate();


        return true;
    }


    public void  setPenColor (int cl) {

        currentPaint = new Paint();
        currentPaint.setStrokeWidth(20);
        currentPaint.setStyle(Paint.Style.STROKE);
        currentPaint.setStrokeJoin(Paint.Join.ROUND);
        currentPaint.setColor(cl);
    }



    public void erase()
    {
       paths.clear();
        latestPath = null;
        invalidate();
    }

    public void undo()
    {


        if (currentIndex>0)  --currentIndex;

        invalidate();
    }

    public void redo() {
        if (currentIndex< paths.size()) ++currentIndex;
        invalidate();

    }


    public void captureAndSend() {

        try {



            getDrawingCache().compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(new File(Environment.getExternalStorageDirectory(),"/screencap.jpg")));

        } catch (Exception e) {
            Log.e("Error--------->", e.toString());
        }
    }



}