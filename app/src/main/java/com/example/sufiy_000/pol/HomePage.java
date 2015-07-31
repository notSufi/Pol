package com.example.sufiy_000.pol;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sufiy_000.pol.classes.AsyncHttpGet;
import com.example.sufiy_000.pol.classes.Post;
import com.example.sufiy_000.pol.classes.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class HomePage extends Fragment {

    ListView m_listView;
    private ArrayAdapter<String> m_adapter;
    private ArrayList<String> m_arrayList = new ArrayList<String>();
    private ArrayList<Post> m_posts = new ArrayList<Post>();

    Home parentActivity;

    public User currentUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_home_page, container, false);

        parentActivity = (Home) getActivity();

        m_listView = (ListView) rootView.findViewById(R.id.ThreadsList);
        m_adapter = new ArrayAdapter<String>(parentActivity.getApplicationContext(),
                R.layout.thread_list_item, m_arrayList);

        m_listView.setAdapter(m_adapter);

        m_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Post post = m_posts.get(position);
                Toast.makeText(parentActivity.getApplicationContext(), post.getTitle(), Toast.LENGTH_SHORT).show();
                Intent viewThread = new Intent(parentActivity.getApplicationContext(), Thread.class);
                viewThread.putExtra("Thread", post);
                startActivity(viewThread);
            }
        });

        String constit = "wealdon";
        String android_id = "test";

        if (currentUser != null) {
            constit = currentUser.getConstituency();
            android_id = currentUser.getAndroidId();
        }
        else {
            Toast.makeText(parentActivity.getApplicationContext(), "Can't find current constituency", Toast.LENGTH_SHORT).show();
        }

        try {
            constit = URLEncoder.encode(constit, "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        GetThreads test = new GetThreads();
        test.execute("http://sufigaffar.com/pol/?query=post&action=return&limit=10&order=timestamp&parent=0&constituency="
                + constit + "&android_id=" + android_id);

        return rootView;
    }

    class GetThreads extends AsyncHttpGet {
        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            m_arrayList.clear();
            m_posts.clear();
            Log.d("Threads Response", response);
            try {
                JSONArray threads = new JSONArray(response);
                for (int i = 0; i < threads.length(); i++){
                    Post post;
                    JSONObject thread = threads.getJSONObject(i);
                    String Title = thread.getString("title");
                    int parentId = thread.getInt("parent");
                    if (parentId > 0) {
                        Title = "Re: " + Title;
                    }
                    int id = thread.getInt("id");
                    String up_votes = thread.getString("upvotes");
                    String creator = thread.getString("creator");
                    String content = thread.getString("content");
                    post = new Post(parentId, creator, up_votes, Title, content);
                    m_posts.add(post);
                    m_arrayList.add(Title);
                    m_adapter.notifyDataSetChanged();
                }
            } catch (Exception e) {
                Log.e("Get Threads Error", e.toString());
                e.printStackTrace();
            }
        }
    }
}