package com.example.sufiy_000.pol;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CandidatesList extends Fragment {

    private ListView m_listView;
    private ArrayAdapter<String> m_adapter;
    private ArrayList<String> m_arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_candidates_list, container, false);

        m_listView = (ListView) rootView.findViewById(R.id.listView2);
        m_arrayList = new ArrayList<String>();
        m_adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.candidate_list_view, m_arrayList);

        m_listView.setAdapter(m_adapter);

        for (int i = 1; i < 100; i++) {
            m_adapter.add("Candidate " + i);
        }

        return rootView;
    }
}