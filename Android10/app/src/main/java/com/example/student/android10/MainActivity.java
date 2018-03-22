package com.example.student.android10;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GetTask mGetTask;
    private SaveTask mSaveTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Activity2.class));
            }
        });

        load();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mGetTask != null) {
            mGetTask.cancel(true);
        }
        if (mSaveTask != null) {
            mSaveTask.cancel(true);
        }
    }

    private void load() {
        mGetTask = new GetTask();
        mGetTask.execute();
    }

    private void edit(Student student) {
        final Fragment2 fragment2 = Fragment2.newInstance(student);
        fragment2.setActionListener(new Fragment2.ActionListener() {
            @Override
            public void save(Student student) {
                mSaveTask = new SaveTask();
                mSaveTask.execute(student);

                fragment2.dismiss();
            }

            @Override
            public void cancel() {
                fragment2.dismiss();
            }
        });

        //getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, fragment2).commit();
        fragment2.show(getSupportFragmentManager(), "dialog");
    }

    public void OnClick(View v) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (v.getId()) {
            case R.id.button:
                edit(new Student());
                break;
        }

        transaction.commit();
    }

    public class GetTask extends AsyncTask<Void, Void, ArrayList<Student>> {

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
        protected ArrayList<Student> doInBackground(Void... voids) {
            return new DataBaseHelper(MainActivity.this).getStudents();
        }

        @Override
        protected void onPostExecute(ArrayList<Student> students) {
            super.onPostExecute(students);

            if (mDialog != null) {
                mDialog.dismiss();
            }

            Fragment1 fragment1 = Fragment1.newInstance(students);
            fragment1.setActionListener(new Fragment1.ActionListener() {
                @Override
                public void onSelect(Student student) {
                    edit(student);
                }
            });

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentLayout, fragment1)
                    .commit();
        }
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
            return new DataBaseHelper(MainActivity.this).save(students[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (mDialog != null) {
                mDialog.dismiss();
            }

            load();
        }
    }
}
