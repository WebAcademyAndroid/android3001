package com.example.student.android12;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AppWidget2ConfigureActivity extends Activity {

    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    private ListView mListView;
    private ArrayList<Student> mStudents;
    private DataBaseHelper mHelper;

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            final Context context = AppWidget2ConfigureActivity.this;

            int position = mListView.getCheckedItemPosition();
            if (position >= 0) {
                Student student = mStudents.get(position);

                Widget widget = new Widget(student.id, mAppWidgetId);
                mHelper.insert(widget);

                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                AppWidget2.updateAppWidget(context, appWidgetManager, mAppWidgetId);

                Intent resultValue = new Intent();
                resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
                setResult(RESULT_OK, resultValue);
                finish();
            } else {
                Toast.makeText(context, "Select student!", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setResult(RESULT_CANCELED);
        setContentView(R.layout.app_widget2_configure);

        mListView = findViewById(R.id.listView);
        findViewById(R.id.add_button).setOnClickListener(mOnClickListener);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }

        mHelper = new DataBaseHelper(this);
        mStudents = mHelper.getStudents();

        ArrayAdapter<Student> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_single_choice,
                android.R.id.text1,
                mStudents);
        mListView.setAdapter(adapter);
    }
}

