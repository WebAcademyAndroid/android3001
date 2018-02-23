package com.example.student.android4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Activity3 extends AppCompatActivity {

    private ListView mListView;
    ArrayList<Student> mStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        mListView = findViewById(R.id.listView);
        init();
    }

    private void init() {
        mStudents = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mStudents.add(new Student("Ivan " + i, "Ivanov " + i, i));
        }

/*        ArrayAdapter<Student> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                items);*/

        final StudentAdapter adapter = new StudentAdapter(this, R.layout.student, mStudents);

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Student student = mStudents.get(position);
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                mStudents.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}
