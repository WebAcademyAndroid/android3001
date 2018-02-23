package com.example.student.android4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class Activity2 extends AppCompatActivity {

    private static String KEY_NAME = "Name";
    private static String KEY_LAST_NAME = "LastName";
    private static String KEY_AGE = "Age";
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        mListView = findViewById(R.id.listView);
        init();
    }

    private void init() {
        ArrayList<HashMap<String, String>> items = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            HashMap<String, String> item = new HashMap<>();
            item.put(KEY_NAME, "Ivan " + i);
            item.put(KEY_LAST_NAME, "Ivanov " + i);
            item.put(KEY_AGE, String.valueOf(i));

            items.add(item);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                items,
                R.layout.student,
                new String[]{KEY_NAME, KEY_LAST_NAME, KEY_AGE},
                new int[]{R.id.textViewFirstName, R.id.textViewLastName, R.id.textViewAge});

        mListView.setAdapter(adapter);
    }
}
