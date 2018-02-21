package com.example.student.android3;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ACTIVITY_2 = 10;

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView2);
        registerForContextMenu(mTextView);
    }

    public void OnClick(View v) {

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        switch (v.getId()) {
            case R.id.button:
                startActivityForResult(new Intent(this, Activity2.class), REQUEST_CODE_ACTIVITY_2);
                break;
            case R.id.button2:
                Toast.makeText(this, "Hello toast!", Toast.LENGTH_LONG).show();
                break;
            case R.id.button3: {
                Toast toast = Toast.makeText(this, "Hello toast!", Toast.LENGTH_LONG);

                ImageView imageView = new ImageView(this);
                imageView.setImageResource(R.mipmap.ic_launcher);

                LinearLayout layout = (LinearLayout) toast.getView();
                layout.addView(imageView, 0);

                toast.setGravity(Gravity.TOP | Gravity.LEFT, 0, 0);
                toast.show();
            }
            break;
            case R.id.button4: {
                View view = getLayoutInflater().inflate(R.layout.toast, null);

                TextView textView = view.findViewById(R.id.textView);
                textView.setText("Hello custom toast!");

                Toast toast = new Toast(this);

                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(view);
                toast.show();
            }
            break;
            case R.id.button5:
                Notification notification = new NotificationCompat.Builder(this)
                        .setAutoCancel(true)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setTicker("Ticker")
                        .setContentTitle("Title")
                        .setContentText("Text")
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setWhen(System.currentTimeMillis())
                        .setContentIntent(pendingIntent)
                        .build();

                notificationManager.notify(1, notification);
                break;
            case R.id.button6:
                new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                                Toast.makeText(MainActivity.this, hour + ":" + minute, Toast.LENGTH_LONG).show();
                            }
                        }, 30, 80, true).show();
                break;
            case R.id.button7:
                PopupMenu menu = new PopupMenu(this, findViewById(R.id.textView2));
                menu.inflate(R.menu.main);
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.action1:
                                Toast.makeText(MainActivity.this, "Action 1 click", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.action2:
                                Toast.makeText(MainActivity.this, "Action 2 click", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.action3:
                                Toast.makeText(MainActivity.this, "Action 3 click", Toast.LENGTH_SHORT).show();
                                return true;
                        }
                        return false;
                    }
                });
                menu.show();
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
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ACTIVITY_2) {
            if (resultCode == RESULT_OK) {
                String text = data.getStringExtra(Activity2.EXTRA_TEXT);
                ((Button) findViewById(R.id.button)).setText(text);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action1:
                Toast.makeText(this, "Action 1 click", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action2:
                Toast.makeText(this, "Action 2 click", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action3:
                Toast.makeText(this, "Action 3 click", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.main, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action1:
                Toast.makeText(this, "Action 1 click", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action2:
                Toast.makeText(this, "Action 2 click", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action3:
                Toast.makeText(this, "Action 3 click", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }
}
