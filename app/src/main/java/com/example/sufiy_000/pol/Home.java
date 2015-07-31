package com.example.sufiy_000.pol;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sufiy_000.pol.classes.AsyncHttpGet;
import com.example.sufiy_000.pol.classes.Candidate;
import com.example.sufiy_000.pol.classes.User;
import com.squareup.okhttp.OkHttpClient;

import java.net.URLEncoder;
import java.util.ArrayList;


public class Home extends FragmentActivity {

    private static final int NUM_PAGES = 3;

    private ViewPager m_Pager;

    private PagerAdapter m_pagerAdapter;

    private TextView m_titleBar;

    private Context m_context;

    private Button m_create_button;

    private HomePage m_homePage = new HomePage();
    private AllPosts m_allPosts = new AllPosts();
    private CandidatesList m_candidatesList = new CandidatesList();

    public ArrayList<Candidate> m_candidates = null;

    public User m_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        m_context = getApplicationContext();
        /*
        android.view.Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        try {
            window.setStatusBarColor(getResources().getColor(R.color.statusColor));
        } catch (Exception e) {
            Log.e("Status bar", "failed");
        }*/

        m_create_button = (Button) findViewById(R.id.new_post);
        m_create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ThreadCreate.class);
                startActivityForResult(intent, 100);
            }
        });

        //Get User
        Intent i = getIntent();
        m_user = (User)i.getSerializableExtra("User");
        Toast.makeText(getApplicationContext(), m_user.getAndroidId(), Toast.LENGTH_LONG).show();

        // Get Components
        m_Pager = (ViewPager)findViewById(R.id.pager);
        m_pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        m_Pager.setAdapter(m_pagerAdapter);
        m_titleBar = (TextView) findViewById(R.id.TitleBar);

        m_candidatesList.currentUser = m_user;
        m_allPosts.currentUser = m_user;
        m_homePage.currentUser = m_user;

        m_titleBar.setText(m_user.getConstituency());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            String title = data.getStringExtra("Title");
            String content = data.getStringExtra("Content");

            try {
                title = URLEncoder.encode(title, "UTF8");
                content = URLEncoder.encode(content, "UTF8");
            } catch (Exception e) {
                e.printStackTrace();
            }

            String baseUrl = "http://sufigaffar.com/pol?query=post&parent=0&tags=generic&action=new&android_id=";
            String url =  m_user.getAndroidId()+"&title="+title+"&content="+content;
            new CreatePost().execute(baseUrl + url);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return m_homePage;
                case 1:
                    return m_allPosts;
                case 2:
                    return m_candidatesList;
                default:
                    return m_homePage;
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    class CreatePost extends AsyncHttpGet {
        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            //After post creation
        }
    }
}
