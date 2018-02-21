package com.example.student.android3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Activity2 extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.example.student.android3.extra.TEXT";
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        mEditText = findViewById(R.id.editText);
        findViewById(R.id.button11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(EXTRA_TEXT, mEditText.getText().toString());

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
