package com.example.student.android11;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

public class StudentView extends RelativeLayout {

    private RequiredEditText mFirstName, mLastName, mAge;
    private Student mStudent;

    public StudentView(Context context) {
        super(context);
        init(context);
    }

    public StudentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.student, this);

        mFirstName = view.findViewById(R.id.editTextFirstName);
        mLastName = view.findViewById(R.id.editTextLastName);
        mAge = view.findViewById(R.id.editTextAge);
    }

    public void clean() {
        mStudent = null;

        mFirstName.setText(null);
        mLastName.setText(null);
        mAge.setText(null);

        mFirstName.setError(null);
        mLastName.setError(null);
        mAge.setError(null);
    }

    public void set(Student student) {
        clean();

        mStudent = student;
        if (mStudent != null) {
            mFirstName.setText(mStudent.FirstName);
            mLastName.setText(mStudent.LastName);
            mAge.setText(String.valueOf(mStudent.Age));
        }
    }

    public Student get() {
        if (mStudent != null) {
            mStudent.FirstName = mFirstName.getText().toString();
            mStudent.LastName = mLastName.getText().toString();
            mStudent.Age = Long.parseLong(mAge.getText().toString());
        }

        return mStudent;
    }

    public boolean validate(){
        return mFirstName.validate() & mLastName.validate() & mAge.validate();
    }
}
