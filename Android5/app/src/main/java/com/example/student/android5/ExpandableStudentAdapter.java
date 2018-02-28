package com.example.student.android5;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ExpandableStudentAdapter extends BaseExpandableListAdapter{

    private int mGroupResource;
    private int mChildResource;
    private ArrayList<Group> mGroups;
    private LayoutInflater mInflater;

    public ExpandableStudentAdapter(Context context, int groupResource, int childResource, ArrayList<Group> groups){
        mGroupResource = groupResource;
        mChildResource = childResource;
        mGroups = groups;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return mGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mGroups.get(groupPosition).Students.length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
         return mGroups.get(groupPosition).Students[childPosition];
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup viewGroup) {
        convertView = mInflater.inflate(mGroupResource, null);
        Group group = (Group) getGroup(groupPosition);

        ((TextView)convertView.findViewById(R.id.textView)).setText(group.Name);

        View indicator = convertView.findViewById(R.id.indicator);
        if(group.Students.length == 0){
            indicator.setVisibility(View.INVISIBLE);
        }else if(isExpanded){
            indicator.setBackgroundColor(Color.RED);
        }else{
            indicator.setBackgroundColor(Color.GREEN);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View convertView, ViewGroup viewGroup) {
        convertView = mInflater.inflate(mChildResource, null);
        Student student = (Student) getChild(groupPosition,childPosition);

        ((TextView)convertView.findViewById(R.id.textViewFirstName)).setText(student.FirstName);
        ((TextView)convertView.findViewById(R.id.textViewLastName)).setText(student.LastName);
        ((TextView)convertView.findViewById(R.id.textViewAge)).setText(String.valueOf(student.Age));

        return convertView;
    }





    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
