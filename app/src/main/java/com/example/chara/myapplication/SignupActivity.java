package com.example.chara.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    private static final String LOG1 ="log1" ;
    EditText ettt1,ettt2,ettt3,ettt4;
    String ss1,ss2,ss3,ss4;
    Context cx1=this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }
    public void signup(View view)
    {
        ettt1=(EditText)findViewById(R.id.editText);
        ettt2=(EditText)findViewById(R.id.editText2);
        ettt3=(EditText)findViewById(R.id.editText5);
        ettt4=(EditText)findViewById(R.id.editText4);
        ss1=ettt1.getText().toString();
        ss2=ettt2.getText().toString();
        ss3=ettt3.getText().toString();
        ss4=ettt4.getText().toString();
        database db1=new database(cx1);
        db1.putinformation(db1,ss1,ss2,ss3,ss4);
        Toast.makeText(getBaseContext(),"registration success",Toast.LENGTH_SHORT).show();

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET}, 10);
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.


                return;
            }
        }
           Intent intent=new Intent(getApplicationContext(),GPS_SERVICE.class);
           startService(intent);
        finish();
    }
}
