package com.example.maliciousapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private static String sUrl = "http://10.0.2.2:8085";

    public static String getFlag() throws Exception {
        String urlParameters = "username=testuser&password=passtestuser123";
        int postDataLength = urlParameters.getBytes(StandardCharsets.UTF_8).length;
        HttpURLConnection conn = (HttpURLConnection) new URL(sUrl + "?" + urlParameters).openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setUseCaches(false);
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String content = "";
        while (true) {
            String line = rd.readLine();
            if (line == null) {
                return content;
            }
            content = content + line + "\n";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.i("flag", getFlag());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
}