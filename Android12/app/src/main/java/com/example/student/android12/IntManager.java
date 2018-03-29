package com.example.student.android12;

import android.content.Context;
import android.content.SharedPreferences;

public class IntManager {
    public static int nextInt(Context context){
        int count;

        SharedPreferences preferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        count = preferences.getInt("Count", 1);

        count++;
        if(count > 999999){
            count = 1;
        }

        editor.putInt("Count", count);
        editor.apply();

        return count;
    }
}
