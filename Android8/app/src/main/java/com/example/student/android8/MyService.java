package com.example.student.android8;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {
    public MyService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();

        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Title")
                .setContentText("Text")
                .setWhen(System.currentTimeMillis())
                .build();

        startForeground(1, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int timeout = intent.getIntExtra("Timeout", 0);
        Toast.makeText(this, String.valueOf(timeout), Toast.LENGTH_SHORT).show();

        PendingIntent pendingIntent = intent.getParcelableExtra("PendingIntent");

        Intent result = new Intent();
        result.putExtra("Unread", 3);

        try {
            pendingIntent.send(this, Activity.RESULT_OK, result);
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    public void test(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "CALL SERVICE", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public class MyBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }
}
