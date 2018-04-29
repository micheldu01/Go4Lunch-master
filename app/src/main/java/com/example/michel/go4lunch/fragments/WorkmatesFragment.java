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
public class WorkmatesFragment extends Fragment {


    //create constructor
    public static WorkmatesFragment newInstance() {
        // Required empty public constructor
        return (new WorkmatesFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workmates, container, false);
    }
}
