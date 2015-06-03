package com.brian.whiteboardtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Stack;

public class WhiteBoardView extends View implements View.OnTouchListener {

    protected Path latestPath;
    protected Stack<Path> paths = new Stack<Path>();
    private Paint currentPaint = new Paint();
    private boolean penDown = false;

    public WhiteBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.setOnTouchListener(this);

        currentPaint.setColor(Color.BLUE);

    }


    @Override
    public void onDraw(Canvas canvas) {
    }

    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                penDown = true;
                latestPath = new Path();
                latestPath.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                penDown = false;
                paths.push(latestPath);
                break;
            case MotionEvent.ACTION_MOVE:
               if (penDown) latestPath.lineTo(event.getX(), event.getY());
               break;

            default:
                return false;
        }

    return true;
    }


    public void  setPenColor (int cl) {
        currentPaint.setColor(cl);
    }
}