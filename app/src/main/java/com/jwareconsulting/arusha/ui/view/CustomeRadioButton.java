package com.jwareconsulting.arusha.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.jwareconsulting.arusha.R;

/**
 * Created by bittu on 9/25/16.
 */

public class CustomeRadioButton extends RadioButton {
    public CustomeRadioButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public CustomeRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);

    }

    public CustomeRadioButton(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomeRadioButton);
            String fontName = a.getString(R.styleable.CustomeRadioButton_fontName1);
            if (fontName != null) {
                Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts" + "/" + fontName);
                setTypeface(myTypeface);
            }
            a.recycle();
        }
    }
}


