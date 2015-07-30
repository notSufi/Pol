package com.example.sufiy_000.pol;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sufiy_000.pol.classes.User;


public class Home extends FragmentActivity {

    private static final int NUM_PAGES = 3;

    private ViewPager m_Pager;

    private PagerAdapter m_pagerAdapter;

    private TextView m_titleBar;

    private Context m_context;

    private HomePage m_homePage = new HomePage();
    private AllPosts m_allPosts = new AllPosts();
    private CandidatesList m_candidatesList = new CandidatesList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        m_context = getApplicationContext();

        //Get User
        Intent i = getIntent();
        User user = (User)i.getSerializableExtra("User");
        Toast.makeText(getApplicationContext(), user.getAndroidId(), Toast.LENGTH_LONG).show();

        // Get Components
        m_Pager = (ViewPager)findViewById(R.id.pager);
        m_pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        m_Pager.setAdapter(m_pagerAdapter);
        m_titleBar = (TextView) findViewById(R.id.TitleBar);

        m_candidatesList.currentUser = user;
        m_titleBar.setText(user.getConstituency());
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
}
