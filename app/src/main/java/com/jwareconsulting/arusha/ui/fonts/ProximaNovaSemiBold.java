
package com.jwareconsulting.arusha.ui.fonts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class ProximaNovaSemiBold extends TextView {

    public ProximaNovaSemiBold(Context context) {
        super(context);
        initializeFont(context);
    }

    private void initializeFont(Context context) {
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/proximanova_semibold.otf");
        this.setTypeface(face);
    }

    public ProximaNovaSemiBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeFont(context);
    }

    public ProximaNovaSemiBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeFont(context);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

}