package com.example.chara.myapplication;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;




public class GPS_SERVICE extends Service {
    Context cx2 = this;
    private static final String LOG12 = "log12";
    private LocationManager locationManager;
    private LocationListener locationlistener;

    public GPS_SERVICE() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Log.d(LOG12, "location manager");
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Log.d(LOG12, "location manager1");
        locationlistener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                database db1 = new database(cx2);
                Log.d(LOG12, "in location");
                db1.updateinformation(db1,location.getLatitude(),location.getLongitude());
            }


            public void onStatusChanged(String provider, int status, Bundle extras) {

            }


            public void onProviderEnabled(String provider) {

            }


            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);

            }
        };
        Log.d(LOG12, "location manager2");


        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationlistener);
        Log.d(LOG12, "location manager3");
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Log.d(LOG12, "location manager4");
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationlistener);
        Log.d(LOG12, "location manager5");
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if(location!=null)
        {
            database db1 = new database(cx2);
            Log.d(LOG12, "in location1");
            db1.updateinformation(db1,location.getLatitude(),location.getLongitude());
        }
        }

}
