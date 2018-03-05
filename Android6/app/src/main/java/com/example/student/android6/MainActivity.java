package com.example.student.android6;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnClick(View v) {
        DataBaseHelper helper = new DataBaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        switch (v.getId()) {
            case R.id.button: {
/*                ContentValues values = new ContentValues();

                values.put("FirstName", "Ivan");
                values.put("LastName", "Ivanov");
                values.put("Age", 33);

                long id = db.insert("Students", null, values);*/
                long id = helper.insert(new Student("Petro", "Petrov", 22));
                Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.button2: {
                ContentValues values = new ContentValues();

                values.put("Age", 44);

                int count = db.update("Students", values, "_id>2", null);
                Toast.makeText(this, String.valueOf(count), Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.button3:
                break;
            case R.id.button4:
                break;
            case R.id.button5:
                break;
            case R.id.button6:
                break;
            case R.id.button7:
                break;
            case R.id.button8:
                break;
        }
    }
}
