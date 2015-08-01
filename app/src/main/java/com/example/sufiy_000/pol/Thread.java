package com.example.sufiy_000.pol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sufiy_000.pol.classes.AsyncHttpGet;
import com.example.sufiy_000.pol.classes.Post;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Thread extends Activity {

    Post m_thread;

    TextView m_title_display;
    TextView m_threadContent;

    ListView m_listView;
    ArrayAdapter<String> m_adapter;
    ArrayList<String> m_comments = new ArrayList<String>();
    //ArrayList<Post> m_comments_post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        Intent i = getIntent();
        m_thread = (Post) i.getSerializableExtra("Thread");

        m_listView = (ListView) findViewById(R.id.CommentList);
        m_adapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.comment, m_comments);

        m_listView.setAdapter(m_adapter);

        new GetComments().execute("http://sufigaffar.com/pol/?query=post&action=return&limit=10&order=timestamp&parent="
                + m_thread.getParentId());

        m_title_display = (TextView) findViewById(R.id.TitleDisplay);
        m_title_display.setText(m_thread.getTitle());
        m_threadContent = (TextView) findViewById(R.id.threadContent);
        m_threadContent.setText(m_thread.getContent());
    }

    class GetComments extends AsyncHttpGet {
        @Override
        protected void onPostExecute(String response) {
            m_comments.clear();
            super.onPostExecute(response);
            Log.d("Threads Response", response);
            try {
                JSONArray threads = new JSONArray(response);
                for (int i = 0; i < threads.length(); i++){
                    JSONObject thread = threads.getJSONObject(i);
                    String content = thread.getString("content");
                    m_comments.add(content);
                    m_adapter.notifyDataSetChanged();
                }
            } catch (Exception e) {
                Log.e("Get Threads Error", e.toString());
                e.printStackTrace();
            }
        }
    }
}