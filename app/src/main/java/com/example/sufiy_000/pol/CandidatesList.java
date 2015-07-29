package com.example.sufiy_000.pol;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CandidatesList.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CandidatesList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CandidatesList extends android.support.v4.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_candidates_list, container, false);

        return rootView;
    }
}