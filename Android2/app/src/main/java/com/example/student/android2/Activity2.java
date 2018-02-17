package com.example.student.android2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    private TextView textView, textViewStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        textView = findViewById(R.id.textView2);
        textViewStudent = findViewById(R.id.textViewStudent);

        Intent intent = getIntent();
        String text = intent.getStringExtra(MainActivity.EXTRA_TEXT);

        textView.setText(text);

        Student student = intent.getParcelableExtra (MainActivity.EXTRA_STUDENT);
        textViewStudent.setText(student.FirstName + " " + student.LastName + " " + student.Age);
    }
}
