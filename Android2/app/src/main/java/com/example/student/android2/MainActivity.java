package com.example.student.android2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;

    public static final String EXTRA_TEXT = "com.example.student.android2.extra.TEXT";
    public static final String EXTRA_STUDENT = "com.example.student.android2.extra.STUDENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);

/*        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(String.valueOf(123));
            }
        });*/
    }

    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                textView.setText(String.valueOf(123));
                break;
            case R.id.button3:
                textView.setText(String.valueOf(321));
                break;
            case R.id.button4: {
                Intent intent = new Intent(this, Activity2.class);
                intent.putExtra(EXTRA_TEXT, editText.getText().toString());

                Student student = new Student("Ivan", "Ivanov", 22);
                intent.putExtra(EXTRA_STUDENT, student);

                startActivity(intent);
            }
            break;
            case R.id.button2: {
                Intent intent = new Intent(this, Activity3.class);
                startActivity(intent);
            }
            break;
        }
    }
}
