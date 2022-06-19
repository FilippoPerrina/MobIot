package com.example.maliciousapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri filepath = getIntent().getData();
        String fileName = filepath.toString().substring(filepath.toString().lastIndexOf("/") + 1).trim();
        String line = read_file(fileName);

        byte[] hash = calcHash(line);
        String hash2 = bin2hex(hash);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("hash", hash2.toLowerCase());
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    public byte[] calcHash(String password) {
        MessageDigest digest=null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        assert digest != null;
        digest.reset();
        return digest.digest(password.getBytes());
    }

    static String bin2hex(byte[] data) {
        return String.format("%0" + (data.length*2) + "X", new BigInteger(1, data));
    }

    public String read_file(String filename) {

        File sdcard = Environment.getExternalStorageDirectory();

        File file = new File(sdcard,filename);

        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
            }
            br.close();
            return text.toString();
        }
        catch (IOException e) {
            return "";
        }

    }


}
