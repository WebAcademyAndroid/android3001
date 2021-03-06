package com.example.student.android7;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnClick(View v) {

        SharedPreferences preferences = null;
        SharedPreferences.Editor editor = null;

        switch (v.getId()) {
            case R.id.button:
                preferences = getPreferences(MODE_PRIVATE);
                editor = preferences.edit();

                editor.putString("Text", "Some text 1");
                editor.commit();
                break;
            case R.id.button2:
                preferences = getSharedPreferences("My Prefs", MODE_PRIVATE);
                editor = preferences.edit();

                editor.putString("Text", "Some text 2");
                editor.commit();
                break;
            case R.id.button3:
                preferences = getPreferences(MODE_PRIVATE);
                String text = preferences.getString("Text", "");
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                break;
            case R.id.button4:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.button5:
                preferences = PreferenceManager.getDefaultSharedPreferences(this);
                String pref = preferences.getString("edit_text_preference_1", "");
                Toast.makeText(this, pref, Toast.LENGTH_SHORT).show();
                break;
            case R.id.button6:
                saveInternalFile("MyFile.txt", "I am internal file data");
                break;
            case R.id.button7:
                Toast.makeText(this, readInternalFile("MyFile.txt"),
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.button8:
                if (hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        File folder = Environment.getExternalStorageDirectory();
                        folder = new File(folder.getAbsolutePath() + "/MyFolder");

                        saveExternalFile(folder, "MyFile2.txt", "File data");
                    }
                }else{
                    requestPermissions();
                }
                break;
            case R.id.button9:
                if (hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        File folder = Environment.getExternalStorageDirectory();
                        folder = new File(folder.getAbsolutePath() + "/MyFolder");

                        if (folder.exists()) {
                            String text11 = readExternalFile(folder, "MyFile2.txt");
                            Toast.makeText(this, text11, Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    requestPermissions();
                }
                break;
            case R.id.button10:
                Student student = new Student("Ivan", "Ivanov", 22);

                Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy HH:mm:ss").create();
                String json = gson.toJson(student);

                saveInternalFile("Student.txt", json);
                break;
            case R.id.button11:
                String json2 = readInternalFile("Student.txt");

                Gson gson2 = new GsonBuilder().setDateFormat("dd.MM.yyyy HH:mm:ss").create();
                Student student2 = gson2.fromJson(json2, Student.class);

                Toast.makeText(this, student2.FirstName, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions = {
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            };

            requestPermissions(permissions, 0);
        }
    }

    private boolean hasPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
        }

        return true;
    }

    private void saveInternalFile(String fileName, String data) {
        try {
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(
                            openFileOutput(fileName, Context.MODE_PRIVATE)));

            writer.write(data);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readInternalFile(String fileName) {
        try {
            StringBuilder builder = new StringBuilder();

            BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput(fileName)));
            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            reader.close();

            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void saveExternalFile(File folder, String fileName, String data) {
        try {
            if (!folder.exists()) {
                folder.mkdirs();
            }

            File file = new File(folder, fileName);

            BufferedWriter writer =
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    new FileOutputStream(file), "UTF8"));

            writer.write(data);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readExternalFile(File folder, String fileName) {
        File file = new File(folder, fileName);

        try {
            if (file.exists()) {
                StringBuilder builder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;

                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }

                reader.close();

                return builder.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
