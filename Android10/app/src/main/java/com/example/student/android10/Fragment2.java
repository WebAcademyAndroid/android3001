package com.example.student.android10;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class Fragment2 extends DialogFragment {

    private static final String EXTRA_STUDENT = "com.example.student.android10.extra.STUDENT";

    private Student mStudent;

    private EditText mEditTextFirstName, mEditTextLastName, mEditTextAge;

    public static Fragment2 newInstance(Student student) {
        Fragment2 fragment = new Fragment2();

        Bundle args = new Bundle();
        args.putParcelable(EXTRA_STUDENT, student);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mStudent = getArguments().getParcelable(EXTRA_STUDENT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);

        mEditTextFirstName = view.findViewById(R.id.editTextFirstName);
        mEditTextLastName = view.findViewById(R.id.editTextLastName);
        mEditTextAge = view.findViewById(R.id.editTextAge);

        view.findViewById(R.id.buttonSave).setOnClickListener(saveClick);
        view.findViewById(R.id.buttonCancel).setOnClickListener(cancelClick);

        init();

        return view;
    }

    private void init() {
        mEditTextFirstName.setText(mStudent.FirstName);
        mEditTextLastName.setText(mStudent.LastName);
        mEditTextAge.setText(String.valueOf(mStudent.Age));
    }

    View.OnClickListener saveClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mStudent.FirstName = mEditTextFirstName.getText().toString();
            mStudent.LastName = mEditTextLastName.getText().toString();
            mStudent.Age = Long.parseLong(mEditTextAge.getText().toString());

            if(mListener != null){
                mListener.save(mStudent);
            }
        }
    };

    View.OnClickListener cancelClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mListener != null){
                mListener.cancel();
            }
        }
    };

    private ActionListener mListener;

    public void setActionListener(ActionListener listener){
        mListener = listener;
    }

    public interface ActionListener{
        void save(Student student);
        void cancel();
    }
}
