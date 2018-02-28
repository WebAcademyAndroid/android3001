package com.example.student.android5;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class RecyclerStudentAdapter extends RecyclerView.Adapter<StudentViewHolder> {

    private int mResource;
    private ArrayList<Student> mStudents;
    private LayoutInflater mInflater;

    public RecyclerStudentAdapter(Context context, int resource, ArrayList<Student> students) {
        mResource = resource;
        mStudents = students;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(mResource, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        final Student student = mStudents.get(position);
        holder.set(student);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.click(student);
                }
            }
        });
    }

    public interface ActionListener {
        void click(Student student);
    }

    private ActionListener mListener;

    public void setActionListener(ActionListener listener) {
        mListener = listener;
    }


    @Override
    public int getItemCount() {
        return mStudents.size();
    }
}
