package com.example.student.android11;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SuperButton extends RelativeLayout {

    public SuperButton(Context context) {
        super(context);
        init(context, null);
    }

    public SuperButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.super_button, this);

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperButton, 0, 0);

            String text = typedArray.getString(R.styleable.SuperButton_text);
            int src = typedArray.getResourceId(R.styleable.SuperButton_src, 0);

            if(text != null){
                TextView textView = view.findViewById(R.id.textView);
                textView.setText(text);
            }
            if(src != 0){
                ImageView imageView = view.findViewById(R.id.imageView);
                imageView.setImageResource(src);
            }

            typedArray.recycle();
        }
    }
}
