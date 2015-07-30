package com.example.sufiy_000.pol;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.TwoLineListItem;

import com.example.sufiy_000.pol.classes.Candidate;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Thomas on 30/07/2015.
 */
public class CandidateAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Candidate> m_candidates;

    public CandidateAdapter(Context context, ArrayList<Candidate> m_candidates) {
        this.context = context;
        this.m_candidates = m_candidates;
    }

    public void Add(String Name, String Party) {
        Log.d("Candidate Adapter", "Adding Candidate");
        m_candidates.add(new Candidate(Name, Party));
    }

    @Override
    public int getCount() {
        return m_candidates.size();
    }

    @Override
    public Object getItem(int position) {
        return m_candidates.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TwoLineListItem twoLineListItem;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            twoLineListItem = (TwoLineListItem) inflater.inflate(
                    android.R.layout.simple_list_item_2, null);
        } else {
            twoLineListItem = (TwoLineListItem) convertView;
        }

        TextView text1 = twoLineListItem.getText1();
        TextView text2 = twoLineListItem.getText2();

        text1.setTextColor(Color.DKGRAY);
        text2.setTextColor(Color.DKGRAY);

        //twoLineListItem.setBackgroundColor();

        text1.setText(m_candidates.get(position).getName());
        text2.setText("" + m_candidates.get(position).getParty());

        return twoLineListItem;
    }
}
