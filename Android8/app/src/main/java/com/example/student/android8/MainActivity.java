package com.example.student.android8;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ServiceConnection mConnection;
    private MyService mService;

    private ListView mListView;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.listView);
        getStudents();
    }

    private void getStudents() {
        mDialog = new ProgressDialog(this);
        mDialog.setMessage("Wait...");
        mDialog.setCancelable(false);
        mDialog.show();

        MyIntentService.getStudents(this);
    }

    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Intent intent = new Intent(this, MyService.class);
                intent.putExtra("Timeout", 15);

                PendingIntent pendingIntent = createPendingResult(1, intent, 0);
                intent.putExtra("PendingIntent", pendingIntent);

                startService(intent);
                break;
            case R.id.button2:
                if (mConnection == null) {
                    mConnection = new ServiceConnection() {
                        @Override
                        public void onServiceConnected(ComponentName name, IBinder service) {
                            mService = ((MyService.MyBinder) service).getService();
                        }

                        @Override
                        public void onServiceDisconnected(ComponentName name) {
                            mService = null;
                        }
                    };

                    Intent intent2 = new Intent(this, MyService.class);
                    bindService(intent2, mConnection, BIND_AUTO_CREATE);
                }

                if (mService != null) {
                    mService.test();
                }
                break;
            case R.id.button3:
                mDialog = new ProgressDialog(this);
                mDialog.setMessage("Wait...");
                mDialog.setCancelable(false);
                mDialog.show();

                MyIntentService.saveStudent(this, new Student("Ivan", "Ivanov", 22));
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                int unread = data.getIntExtra("Unread", 0);
                Toast.makeText(this, String.valueOf(unread), Toast.LENGTH_SHORT).show();
            } else if (requestCode == MyIntentService.REQUEST_CODE_GET_STUDENTS) {
                ArrayList<Student> students = data.getParcelableArrayListExtra(MyIntentService.EXTRA_STUDENTS);

                ArrayAdapter<Student> adapter = new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        students);
                mListView.setAdapter(adapter);

                if(mDialog != null){
                    mDialog.dismiss();
                }
            }else if (requestCode == MyIntentService.REQUEST_CODE_SAVE_STUDENT) {
                if(mDialog != null){
                    mDialog.dismiss();
                }

                getStudents();
            }
        }
    }
}
