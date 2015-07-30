package com.example.sufiy_000.pol;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.example.sufiy_000.pol.classes.AsyncHttpGet;
import com.example.sufiy_000.pol.classes.Candidate;
import com.example.sufiy_000.pol.classes.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.LogRecord;

public class CandidatesList extends Fragment {

    private ListView m_listView;
    private CandidateAdapter m_adapter;
    private ArrayList<Candidate> m_arrayList;

    Home parentActivity;

    public User currentUser;
    String constitEncoded;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_candidates_list, container, false);

        m_listView = (ListView) rootView.findViewById(R.id.listView2);
        m_arrayList = new ArrayList<Candidate>();
        //m_adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
             //   R.layout.candidate_list_view, m_arrayList);

        parentActivity = (Home)getActivity();

        m_adapter = new CandidateAdapter(getActivity().getApplicationContext(), m_arrayList);

        m_listView.setAdapter(m_adapter);

        //if (parentActivity.m_candidates != null) {
        // m_arrayList = parentActivity.m_candidates;
        //   m_adapter.notifyDataSetChanged();
        //} else {
            String constit = currentUser.getConstituency();

            if (constit == null) {
                constit="Birmingham,%20Ladywood";
                Toast.makeText(getActivity().getApplicationContext(), "Constituency not found", Toast.LENGTH_SHORT).show();
            }

        try {
            constitEncoded = URLEncoder.encode(constit, "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        new GetCandidates().execute("http://sufigaffar.com/pol/?query=candidates&constituency=" + constitEncoded);
        //}

        return rootView;
    }

    class GetCandidates extends AsyncHttpGet {
        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            //testView.setText(response.message());
            Intent intent = new Intent(getActivity().getApplicationContext(), Home.class);
            try {
                Log.d("Response msg", response);
                JSONArray candidateJson = new JSONArray(response);
                Log.d("Response Array", candidateJson.toString());
                for (int i = 0; i < candidateJson.length(); i++) {
                    JSONObject candidate = candidateJson.getJSONObject(i);
                    Log.d("Response Candidate", candidate.toString());
                    String name = candidate.getString("name");
                    Log.d("Candidate Name", name);
                    String party = candidate.getString("party");
                    Log.d("Candidate Party", party);
                    m_arrayList.add(new Candidate(name, party));
                    m_adapter.notifyDataSetChanged();
                    parentActivity.m_candidates = m_arrayList;
                    //AddContent(new Candidate(name,party));
                }

            } catch (Exception e) {
                Log.e("Welcome", e.toString());
                Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("Welcome", Boolean.toString(e.getMessage() == null));
            }
            //intent.putExtra("User", response);
        }
    }
}