package com.example.student.android12;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int id = intent.getExtras().getInt("id");
        Toast.makeText(context, String.valueOf(id), Toast.LENGTH_SHORT).show();
    }
}
