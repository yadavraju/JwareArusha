
package com.jwareconsulting.arusha.ui.fonts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class ProximaNovaLight extends TextView {

    public ProximaNovaLight(Context context) {
        super(context);
        initializeFont(context);
    }

    private void initializeFont(Context context) {
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/ProximaNova-Light.otf");
        this.setTypeface(face);
    }

    public ProximaNovaLight(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeFont(context);
    }

    public ProximaNovaLight(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeFont(context);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

}