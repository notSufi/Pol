package com.example.sufiy_000.pol;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sufiy_000.pol.classes.AsyncHttpGet;
import com.example.sufiy_000.pol.classes.Candidate;
import com.example.sufiy_000.pol.classes.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class AllPosts extends android.support.v4.app.Fragment {

    ListView m_listView;
    private ArrayAdapter<String> m_adapter;
    private ArrayList<String> m_arrayList = new ArrayList<String>();

    Home parentActivity;

    public User currentUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_all_posts, container, false);

        parentActivity = (Home) getActivity();

        m_listView = (ListView) rootView.findViewById(R.id.ThreadsList);
        m_adapter = new ArrayAdapter<String>(parentActivity.getApplicationContext(),
                R.layout.thread_list_item, m_arrayList);

        m_listView.setAdapter(m_adapter);

        GetThreads test = new GetThreads();
        test.execute("http://sufigaffar.com/pol/?query=post&action=return&limit=10&order=timestamp&parent=0&constituency=wealden");

        return rootView;
    }

    class GetThreads extends AsyncHttpGet {
        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            Log.d("Threads Response", response);
            try {
                JSONArray threads = new JSONArray(response);
                for (int i = 0; i < threads.length(); i++){
                    JSONObject thread = threads.getJSONObject(i);
                    String Title = thread.getString("title");
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
