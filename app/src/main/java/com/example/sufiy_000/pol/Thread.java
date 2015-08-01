package com.example.sufiy_000.pol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sufiy_000.pol.classes.AsyncHttpGet;
import com.example.sufiy_000.pol.classes.Post;
import com.example.sufiy_000.pol.classes.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;

public class Thread extends Activity {

    Post m_thread;

    TextView m_title_display;
    TextView m_threadContent;

    ListView m_listView;
    ArrayAdapter<String> m_adapter;
    ArrayList<String> m_comments = new ArrayList<String>();
    //ArrayList<Post> m_comments_post;

    User currentUser;

    Button m_new_post_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        Intent i = getIntent();
        m_thread = (Post) i.getSerializableExtra("Thread");
        currentUser = (User) i.getSerializableExtra("User");

        m_listView = (ListView) findViewById(R.id.CommentList);
        m_adapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.comment, m_comments);

        m_listView.setAdapter(m_adapter);

        m_new_post_btn = (Button) findViewById(R.id.submit_btn);
        m_new_post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ThreadCreate.class);
                startActivityForResult(intent, 100);
            }
        });

        GetComments getComments = new GetComments();
        getComments.execute("http://sufigaffar.com/pol/?query=post&action=return&limit=10&order=timestamp&parent="
                + m_thread.getId());

        m_title_display = (TextView) findViewById(R.id.TitleDisplay);
        m_title_display.setText(m_thread.getTitle());
        m_threadContent = (TextView) findViewById(R.id.threadContent);
        m_threadContent.setText(m_thread.getContent());
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

            String baseUrl = "http://sufigaffar.com/pol?query=post&parent=" + m_thread.getId() + "&tags=generic&action=new&android_id=";
            String url =  currentUser.getAndroidId()+"&content="+content;
            new CreatePost().execute(baseUrl + url);
        }
    }

    class CreatePost extends AsyncHttpGet {
        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            //After post creation
        }
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