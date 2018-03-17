package com.example.student.android9_1;


import android.app.ProgressDialog;
import android.content.ContentValues;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Student>> {

    private ListView mListView;
    private ProgressDialog mDialog;

    private ArrayList<Student> mStudents;
    private SaveTask mSaveTask;
    private DeleteTask mDeleteTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.listView);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSaveTask = new SaveTask();
                mSaveTask.execute(new Student("Petr", "Petrov", 33));
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Student student = mStudents.get(position);

                mDeleteTask = new DeleteTask();
                mDeleteTask.execute(student.id);

                return true;
            }
        });

        loadStudents(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mSaveTask != null) {
            mSaveTask.cancel(false);
        }
        if (mDeleteTask != null) {
            mDeleteTask.cancel(false);
        }
    }

    private void loadStudents(boolean restart) {
        mDialog = new ProgressDialog(MainActivity.this);
        mDialog.setMessage("Wait...");
        mDialog.setCancelable(false);
        mDialog.show();

        if (restart) {
            getSupportLoaderManager().restartLoader(0, null, this);
        } else {
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }

    @Override
    public Loader<ArrayList<Student>> onCreateLoader(int id, Bundle args) {
        return new StudentLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Student>> loader, ArrayList<Student> data) {
        mStudents = data;

        ArrayAdapter<Student> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                data);
        mListView.setAdapter(adapter);

        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Student>> loader) {

    }


    public class SaveTask extends AsyncTask<Student, Void, Boolean> {
        private ProgressDialog mDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mDialog = new ProgressDialog(MainActivity.this);
            mDialog.setMessage("Wait...");
            mDialog.setCancelable(false);
            mDialog.show();
        }

        @Override
        protected Boolean doInBackground(Student... students) {
            boolean result = false;

            try {
                Student student = students[0];
                ContentValues values = new ContentValues();

                values.put(Student.COLUMN_FIRST_NAME, student.FirstName);
                values.put(Student.COLUMN_LAST_NAME, student.LastName);
                values.put(Student.COLUMN_AGE, student.Age);

                Uri uri = Uri.parse("content://com.example.student.android9/students");
                Uri studentUri = getContentResolver().insert(uri, values);

                if (studentUri != null) {
                    result = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (mDialog != null) {
                mDialog.dismiss();
            }

            loadStudents(true);
        }
    }

    public class DeleteTask extends AsyncTask<Long, Void, Boolean> {
        private ProgressDialog mDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mDialog = new ProgressDialog(MainActivity.this);
            mDialog.setMessage("Wait...");
            mDialog.setCancelable(false);
            mDialog.show();
        }

        @Override
        protected Boolean doInBackground(Long... longs) {
            boolean result = false;

            try {
                long id = longs[0];
                Uri uri = Uri.parse("content://com.example.student.android9/students/" + String.valueOf(id));

                int count = getContentResolver().delete(uri, null, null);

                if (count == 1) {
                    result = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (mDialog != null) {
                mDialog.dismiss();
            }

            loadStudents(true);
        }
    }
}
