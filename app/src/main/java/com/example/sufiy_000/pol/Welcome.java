package com.example.sufiy_000.pol;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sufiy_000.pol.classes.AsyncHttpGet;
import com.example.sufiy_000.pol.classes.User;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static android.graphics.Color.WHITE;
import static android.graphics.PorterDuff.Mode.MULTIPLY;
import static android.graphics.PorterDuff.Mode.OVERLAY;
import static android.graphics.PorterDuff.Mode.SCREEN;

//import android.view.Menu;
//import android.view.MenuItem;
//import android.location.LocationListener;
//import android.location.LocationManager;

public class Welcome extends Activity {

    private String text;

    private Handler m_Handler = new Handler();
    private boolean CanSwitch = false;
    TextView testView;
    ProgressBar m_UserLoginProgress;

    Location currentLocation = null;
    LocationManager lm = null;
    LocationListener ll = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Check if user exists

        testView = (TextView) findViewById(R.id.textView2);
        m_UserLoginProgress = (ProgressBar) findViewById(R.id.UserLoginSpinner);

        /*
        if (m_UserLoginProgress != null) {
            //m_UserLoginProgress.getProgressDrawable().setColorFilter(WHITE, OVERLAY);
            m_UserLoginProgress.getIndeterminateDrawable().setColorFilter(Color.WHITE, SCREEN);
        }*/

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

        lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        ll = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                currentLocation = location;
                if (location != null) {
                    doStuff();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, ll);
    }

    boolean once = false;

    private void doStuff () {
        if (!once) {
            String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                    Settings.Secure.ANDROID_ID);

            //Network call
            new testHttp().execute("http://sufigaffar.com/pol/?query=user&action=new&android_id=" +
                    android_id + "&lat=" + String.valueOf(currentLocation.getLatitude()) + "&long=" + String.valueOf(currentLocation.getLongitude()));


            once = true; //Make sure to stop more requests for location
        }
        else {
            Log.d("Welcome", "User already logged in!");
        }
    }

    class testHttp extends AsyncHttpGet {
        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            //testView.setText(response.message());
            Intent intent = new Intent(getApplicationContext(), Home.class);
            try {
                Log.d("Response msg", response);
                JSONObject userJson = new JSONObject(response);

                String android_id = userJson.getString("android_id");
                Date date = new Date();
                date.setTime(userJson.getLong("signup_time"));
                boolean admin = userJson.getBoolean("admin");
                User currentUser = new User(android_id, date, admin);

                intent.putExtra("User", currentUser);
                lm.removeUpdates(ll);
                startActivity(intent);
            } catch (Exception e) {
                Log.e("Welcome", e.toString());
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("Welcome", Boolean.toString(e.getMessage() == null));
                once = false;
            }
            //intent.putExtra("User", response);
        }
    }
}
