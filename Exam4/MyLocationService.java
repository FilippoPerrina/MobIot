package com.example.maliciousapp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyLocationService extends Service implements LocationListener{

    LocationManager locationManager;

    @Override
    public void onCreate() {

        Location currLoc = getCurrentLocation(); // put your magic here
        Intent i = new Intent();
        i.setAction("com.mobiotsec.intent.action.LOCATION_ANNOUNCEMENT");
        i.putExtra("location", currLoc);
        sendBroadcast(i);

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return startId;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    Location getCurrentLocation() {
        Location location = null;
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return location;
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
        return location;
    }

    public void onLocationChanged(Location location) {
    }
}
