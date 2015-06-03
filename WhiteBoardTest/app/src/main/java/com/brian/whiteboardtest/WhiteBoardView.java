package com.brian.whiteboardtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Stack;

public class WhiteBoardView extends View implements View.OnTouchListener {

    protected Path latestPath;
    protected Stack<Path> paths = new Stack<Path>();


    public WhiteBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }


    @Override
    public void onDraw(Canvas canvas) {
    }

    public boolean onTouch(View view, MotionEvent event) {
        return true;
    }

}