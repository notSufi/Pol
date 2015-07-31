package com.example.sufiy_000.pol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sufiy_000.pol.classes.Post;

public class Thread extends Activity {

    Post m_thread;

    TextView m_threadContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        Intent i = getIntent();
        m_thread = (Post) i.getSerializableExtra("Thread");

        m_threadContent = (TextView) findViewById(R.id.threadContent);
        m_threadContent.setText(m_thread.getContent());
    }
}
