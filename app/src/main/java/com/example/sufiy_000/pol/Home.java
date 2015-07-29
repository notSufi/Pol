package com.example.sufiy_000.pol;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.sufiy_000.pol.classes.User;


public class Home extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent i = getIntent();

        User user = (User)i.getSerializableExtra("User");


        Toast.makeText(getApplicationContext(), user.getAndroidId(), Toast.LENGTH_LONG).show();
    }
}
