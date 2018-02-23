package com.example.student.android4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Activity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        Spinner spinner = findViewById(R.id.spinner);
        String[] items = new String[]{"q", "w", "e", "r", "t", "y", "u", "i", "o", "p"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.student,
                R.id.textViewAge,
                items);

        spinner.setAdapter(adapter);
    }
}
