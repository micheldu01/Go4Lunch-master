package com.example.michel.go4lunch.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.michel.go4lunch.R;
import com.example.michel.go4lunch.recyclerview.ItemClickSupport;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkmatesFragment extends Fragment {



    // IMPLEMENT RECYCLER VIEW
    @BindView(R.id.list_view_recyclerView)RecyclerView recyclerView;

    // IMPLEMENT REFRESH LAYOUT
    @BindView(R.id.list_view_swipe_refresh)SwipeRefreshLayout refreshLayout;



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

        // IMPLEMENT VALUES WITH BUTTER KNIFE
        ButterKnife.bind(this,view);






        return view;
    }

    // METHOD SHOW PROFILE CHOICE
    private void showProfileWithChoice(){



    }

    // MEHTOD CLICK ON RECYCLER VIEW
    private void configureOnClickRecyclerView() {
        ItemClickSupport.addTo(recyclerView, R.layout.fragment_list_view)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                        // ON CLICK INTENT

                    }
                });

    }


    // SWIPE REFRESH METHOD
    private void configureSwipeRefreshLayout() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // SHOW LIST RESTAURANT

            }
        });
    }

}
