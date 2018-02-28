package com.example.student.android5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.Random;

public class Activity4 extends AppCompatActivity {

    private ExpandableListView mListView;
    private ArrayList<Group> mGroups;
    private ExpandableStudentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        mListView = findViewById(R.id.listView);

        Random random = new Random();
        mGroups = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            Group group = new Group();
            group.Name = "Group " + i;

            group.Students = new Student[random.nextInt(10)];
            for (int j = 0; j < group.Students.length; j++) {
                group.Students[j] = new Student("Ivan" + i + j, "Ivanov " + i + j, i + j);
            }

            mGroups.add(group);
        }

        mAdapter = new ExpandableStudentAdapter(this, R.layout.group, R.layout.students, mGroups);
        mListView.setAdapter(mAdapter);

        mListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });
        mListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });
        mListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view,
                                        int groupPosition, int childPosition, long id) {
                return false;
            }
        });
        mListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view,
                                        int groupPosition, long id) {
                if(expandableListView.isGroupExpanded(groupPosition)){
                    expandableListView.collapseGroup(groupPosition);
                }else {
                    expandableListView.expandGroup(groupPosition);
                }
                return true;
            }
        });
    }
}
