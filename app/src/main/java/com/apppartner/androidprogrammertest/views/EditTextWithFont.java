package com.apppartner.androidprogrammertest.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import com.apppartner.androidprogrammertest.R;

/**
 * Created by vipulmittal on 05/11/15.
 */
public class EditTextWithFont extends EditText {
    private String font;

    public EditTextWithFont(Context context, AttributeSet attrs) {
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


    public EditTextWithFont(Context context) {
        this(context, null);
    }

}