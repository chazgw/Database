package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    // Database file name
    private static final String DATABASE_NAME = "FeedReaderDbHelper.db";
    public static final int DATABASE_VERSION = 2;
    FeedReaderDbHelper databaseHelper;

    // Widgets
    Button insertRecordButton;
    Button viewRecordButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertRecordButton = findViewById(R.id.insertRecordButton);
        viewRecordButton = findViewById(R.id.viewRecordButton);

        databaseHelper = new FeedReaderDbHelper(this,
                DATABASE_NAME,
                null,
                DATABASE_VERSION);

        insertRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Insert hardcoded record
                databaseHelper.insert("The First Movie","1976");

                // Open New Activity
                //Intent intent = new Intent(MainActivity.this, NewRecord.class);
                //startActivity(intent);

            }

        });

        viewRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                displayMovieName();

            }

        });

    }

    // https://developer.android.com/training/data-storage/sqlite#ReadDbRow
    private void displayMovieName() {

        Cursor cursor = databaseHelper.getData();

        Log.d(TAG, "displayMovieName()");

        while (cursor.moveToNext()) {
            // or databaseHelper.getData().moveToNext() { // ... }
            Log.d(TAG,  cursor.getString(1));
            // the following line gives an error:
            //Log.d(TAG,  "X"+cursor.getString(cursor.getColumnIndex("yearMade")));
            Log.d(TAG,  "X"+cursor.getString(cursor.getColumnIndexOrThrow("yearMade")));

            //Log.d(TAG, cursor.getColumnIndex("yearMade"));

        }

        cursor.close();

    }
}