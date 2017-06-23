
package com.jwareconsulting.arusha.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class RobotoBold extends TextView {

    public RobotoBold(Context context) {
        super(context);
        initializeFont(context);
    }

    private void initializeFont(Context context) {
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/roboto_bold.ttf");
        this.setTypeface(face);
    }

    public RobotoBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeFont(context);
    }

    public RobotoBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeFont(context);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

}