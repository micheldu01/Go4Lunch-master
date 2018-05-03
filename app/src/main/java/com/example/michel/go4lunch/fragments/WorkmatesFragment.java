package com.example.michel.go4lunch.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.michel.go4lunch.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkmatesFragment extends Fragment {

    //@BindView(R.id.workmatesFragment_text)TextView textView;
    private TextView textView;

    //create constructor
    public static WorkmatesFragment newInstance() {
        // Required empty public constructor
        return (new WorkmatesFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workmates, container, false);

        ButterKnife.bind(this,view);

        textView = (TextView) view.findViewById(R.id.workmatesFragment_text);


        Log.e("workmates", "test fragment " + textView);

        textView.setText("je suis un fragment");


        return view;
    }
}
