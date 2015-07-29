package com.example.sufiy_000.pol;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sufiy_000.pol.classes.AsyncHttpGet;
import com.example.sufiy_000.pol.classes.User;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

//import android.view.Menu;
//import android.view.MenuItem;
//import android.location.LocationListener;
//import android.location.LocationManager;

public class Welcome extends Activity {

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

        double aLong;
        double lat;

        if(false){
            text = "Location not found";
        }else {
            //m_long = m_location.getLongitude();
            //m_lat = m_location.getLatitude();

            aLong = 0;
            lat = 51;

            text = String.valueOf(lat)+","+String.valueOf(aLong);
            CanSwitch = true;
        }

        String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);

        //Network call
        new testHttp().execute("http://sufigaffar.com/pol/?query=user&action=new&android_id=" +
                android_id + "&lat=" + String.valueOf(lat) + "&long=" + String.valueOf(aLong));

       // testView.setText(text);

        //TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
        //String android_id = tm.getDeviceId();

        //testView.setText(android_id);

        //Calendar c = Calendar.getInstance();
        //int seconds = c.get(Calendar.SECOND);

        //Date d = new Date();
        //d.setTime(seconds);

        //m_user = new User(android_id,d,0);

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
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            //testView.setText(response.message());
            Intent intent = new Intent(getApplicationContext(), Home.class);
            try {
                Log.e("Response msg", response);
                JSONObject userJson = new JSONObject(response);

                String android_id = userJson.getString("android_id");
                Date date = new Date();
                date.setTime(userJson.getLong("signup_time"));
                boolean admin = userJson.getBoolean("admin");
                User currentUser = new User(android_id, date, admin);

                intent.putExtra("User", currentUser);
                startActivity(intent);
            } catch (Exception e) {
                Log.e("Welcome", e.toString());
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("Welcome", Boolean.toString(e.getMessage() == null));
            }
            //intent.putExtra("User", response);
        }
    }
}
