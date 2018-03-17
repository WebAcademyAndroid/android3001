package com.example.student.android9;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Student>> {

    private SaveTask mSaveTask;
    private SaveManyTask mSaveManyTask;

    private ListView mListView;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.listView);
        loadStudents(false);
    }

    private void loadStudents(boolean restart){
        mDialog = new ProgressDialog(MainActivity.this);
        mDialog.setMessage("Wait...");
        mDialog.setCancelable(false);
        mDialog.show();

        if(restart){
            getSupportLoaderManager().restartLoader(0, null, this);
        }else {
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mSaveTask != null) {
            mSaveTask.cancel(true);
        }
        if (mSaveManyTask != null) {
            mSaveManyTask.cancel(true);
        }
    }

    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                mSaveTask = new SaveTask();
                mSaveTask.execute(new Student("Ivan", "Ivanov", 33));
                break;
            case R.id.button2:
                mSaveManyTask = new SaveManyTask();
                mSaveManyTask.execute(new Student("Ivan", "Ivanov", 33),
                        new Student("Ivan", "Ivanov", 33),
                        new Student("Ivan", "Ivanov", 33),
                        new Student("Ivan", "Ivanov", 33),
                        new Student("Ivan", "Ivanov", 33),
                        new Student("Ivan", "Ivanov", 33),
                        new Student("Ivan", "Ivanov", 33),
                        new Student("Ivan", "Ivanov", 33),
                        new Student("Ivan", "Ivanov", 33),
                        new Student("Ivan", "Ivanov", 33),
                        new Student("Ivan", "Ivanov", 33),
                        new Student("Ivan", "Ivanov", 33),
                        new Student("Ivan", "Ivanov", 33),
                        new Student("Ivan", "Ivanov", 33));
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
            case R.id.button9:
                break;
            case R.id.button10:
                break;
        }
    }

    @Override
    public Loader<ArrayList<Student>> onCreateLoader(int id, Bundle args) {
        return new StudentLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Student>> loader, ArrayList<Student> data) {
        ArrayAdapter<Student> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                data);
        mListView.setAdapter(adapter);

        if(mDialog != null){
            mDialog.dismiss();
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Student>> loader) {

    }


    public class SaveTask extends AsyncTask<Student, Void, Long> {

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
        protected Long doInBackground(Student... students) {
            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!isCancelled()) {

            }
            Student student = students[0];
            DataBaseHelper helper = new DataBaseHelper(MainActivity.this);

            return helper.insert(student);
        }

        @Override
        protected void onPostExecute(Long id) {
            super.onPostExecute(id);

            if (mDialog != null) {
                mDialog.dismiss();
            }

            //Toast.makeText(MainActivity.this, String.valueOf(id), Toast.LENGTH_SHORT).show();
            ((Button) findViewById(R.id.button)).setText(String.valueOf(id));
            loadStudents(true);
        }
    }

    public class SaveManyTask extends AsyncTask<Student, Integer, Void> {

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
        protected Void doInBackground(Student... students) {
            DataBaseHelper helper = new DataBaseHelper(MainActivity.this);

            int count = 1;
            for (Student student : students) {
                if (!isCancelled()) {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    helper.insert(student);
                    publishProgress(count, students.length);
                    count++;
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            mDialog.setMessage(String.format("Saved %d students from %d", values[0], values[1]));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (mDialog != null) {
                mDialog.dismiss();
            }

            loadStudents(true);
        }
    }
}
