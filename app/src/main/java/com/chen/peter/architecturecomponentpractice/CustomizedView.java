package com.chen.peter.architecturecomponentpractice;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

public class CustomizedView extends View{

    public CustomizedView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
