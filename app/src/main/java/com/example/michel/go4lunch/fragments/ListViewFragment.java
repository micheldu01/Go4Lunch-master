package com.example.michel.go4lunch.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michel.go4lunch.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListViewFragment extends Fragment {


    //create constructor
    public static ListViewFragment newInstance() {
        // Required empty public constructor
        return (new ListViewFragment());
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);




        return view;
    }

}
