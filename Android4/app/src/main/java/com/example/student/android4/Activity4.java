package com.example.student.android4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Activity4 extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        mListView = findViewById(R.id.listView);
        //mListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        mListView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        findViewById(R.id.buttonClick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int pos = mListView.getCheckedItemPosition();
                //Toast.makeText(Activity4.this, String.valueOf(pos),Toast.LENGTH_LONG).show();
                String res = "";
                SparseBooleanArray items = mListView.getCheckedItemPositions();
                for(int i=0;i<items.size();i++){
                    if(items.get(i)){
                        res += String.valueOf(i);
                    }
                }
                Toast.makeText(Activity4.this, res, Toast.LENGTH_SHORT).show();
            }
        });

        init();
    }

    private void init() {
        String[] items = new String[]{"q", "w", "e", "r", "t", "y", "u", "i", "o", "p"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                android.R.id.text1,
                items);

        mListView.setAdapter(adapter);
    }
}
