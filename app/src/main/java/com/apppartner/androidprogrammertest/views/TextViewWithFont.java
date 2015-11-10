package com.apppartner.androidprogrammertest.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.apppartner.androidprogrammertest.R;

/**
 * Created by vipulmittal on 05/11/15.
 */
public class TextViewWithFont extends TextView {
    private String font;

    public TextViewWithFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.TextViewWithFont,
                0, 0);

        try {
            font = a.getString(R.styleable.TextViewWithFont_font);
        } finally {
            a.recycle();
        }
        if (font != null) {
            Typeface custom_font = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + font);
            this.setTypeface(custom_font);
        }
    }

    public TextViewWithFont(Context context) {
        this(context, null);
    }

}