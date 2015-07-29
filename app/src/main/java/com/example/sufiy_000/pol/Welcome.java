package com.example.sufiy_000.pol;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.support.v7.app.ActionBarActivity;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sufiy_000.pol.classes.AsyncHttpGet;
import com.example.sufiy_000.pol.classes.User;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

//import android.view.Menu;
//import android.view.MenuItem;
//import android.location.LocationListener;
//import android.location.LocationManager;

public class Welcome extends ActionBarActivity {

    private double m_lat;
    private double m_long;
    private String text;

    private Handler m_Handler = new Handler();
    private boolean CanSwitch = true;

    //private Location m_location = new Location();

    User m_user;
    TextView testView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Check if user exists

        testView = (TextView) findViewById(R.id.textView2);

       /* Location m_deviceLocation = new Location("");
        double m_lat = m_deviceLocation.getLatitude();
        double m_long = m_deviceLocation.getLongitude();*/

        //LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        //Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        //LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, m_locationListener);

       // m_location.setLatitude(51);
       // m_location.setLongitude(0

        if(false){
            text = "Location not found";
        }else {
            //m_long = m_location.getLongitude();
            //m_lat = m_location.getLatitude();
            m_long = 0;
            m_lat = 51;
            text = String.valueOf(m_lat)+","+String.valueOf(m_long);
            CanSwitch = true;
        }

        //Network call
        new testHttp().execute("http://google.com");

       // testView.setText(text);

        TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
        String android_id = tm.getDeviceId();

        Calendar c = Calendar.getInstance();
        int seconds = c.get(Calendar.SECOND);

        Date d = new Date();
        d.setTime(seconds);

        m_user = new User(android_id,d,0);

        m_Handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                doStuff();
            }
        }, 2000);
    }

    private void doStuff () {
        if (CanSwitch) {
            //-Intent intent = new Intent(this, Home.class);
            //startActivity(intent);
        }
        else {
            Toast.makeText(this, "Switch activity now", Toast.LENGTH_SHORT).show();
        }
    }

    class testHttp extends AsyncHttpGet {
        @Override
        protected void onPostExecute(Response response) {
            super.onPostExecute(response);
            testView.setText(response.message());
        }
    }
}
