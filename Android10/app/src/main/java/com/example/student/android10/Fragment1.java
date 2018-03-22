package com.example.student.android10;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Fragment1 extends Fragment {

    private static final String EXTRA_STUDENTS = "com.example.student.android10.extra.STUDENTS";

    private ListView mListView;
    private ArrayList<Student> mStudents;

    public static Fragment1 newInstance(ArrayList<Student> students) {
        Fragment1 fragment = new Fragment1();

        Bundle args = new Bundle();
        args.putParcelableArrayList(EXTRA_STUDENTS, students);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mStudents = getArguments().getParcelableArrayList(EXTRA_STUDENTS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);

        mListView = view.findViewById(R.id.listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = mStudents.get(position);

                if(mListener != null){
                    mListener.onSelect(student);
                }
            }
        });

        ArrayAdapter<Student> adapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                mStudents);

        mListView.setAdapter(adapter);

        return view;
    }


    private ActionListener mListener;

    public void setActionListener(ActionListener listener){
        mListener = listener;
    }

    public interface ActionListener{
        void onSelect(Student student);
    }
}
