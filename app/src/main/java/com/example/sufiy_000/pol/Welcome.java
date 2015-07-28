package com.example.sufiy_000.pol;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

public class Welcome extends ActionBarActivity {

    private double m_lat;
    private double m_long;
    private String text;

    private Handler m_Handler = new Handler();
    private boolean CanSwitch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Check if user exists

        TextView testView = (TextView) findViewById(R.id.textView2);

       /* Location m_deviceLocation = new Location("");
        double m_lat = m_deviceLocation.getLatitude();
        double m_long = m_deviceLocation.getLongitude();*/

        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location == null){
            text = "Location not found";
        }else {
            m_long = location.getLongitude();
            m_lat = location.getLatitude();
            text = String.valueOf(m_lat)+","+String.valueOf(m_long);
            CanSwitch = true;
        }

        testView.setText(text);

        m_Handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                doStuff();
            }
        }, 2000);
    }

    private void doStuff () {
        if (CanSwitch) {
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Switch activity now", Toast.LENGTH_SHORT).show();
        }
    }
}
