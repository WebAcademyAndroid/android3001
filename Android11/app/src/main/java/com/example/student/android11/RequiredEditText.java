package com.example.student.android11;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;

public class RequiredEditText extends android.support.v7.widget.AppCompatEditText {

    private boolean mRequired;

    public RequiredEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RequiredEditText, 0, 0);
        mRequired = typedArray.getBoolean(R.styleable.RequiredEditText_required, false);

        typedArray.recycle();
    }

    public boolean isRequired() {
        return mRequired;
    }

    public void setRequired(boolean required) {
        this.mRequired = required;
    }

    public boolean validate() {
        if (mRequired && getText().length() == 0) {
            setError("Required field!");
            return false;
        }

        return true;
    }
}
