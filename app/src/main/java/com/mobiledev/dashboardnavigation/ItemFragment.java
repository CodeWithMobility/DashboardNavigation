package com.mobiledev.dashboardnavigation;

/**
 * Created by manu on 1/7/2018.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by manu on 1/6/2018.
 */

public class ItemFragment extends Fragment {
    // Store instance variables
    private String title;
    private int page;
    private RecyclerView recyclerView;

    // newInstance constructor for creating fragment with arguments
    public static ItemFragment newInstance(int page, String title) {
        ItemFragment fragmentFirst = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_demo_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        CustomAdapter customAdapter = new CustomAdapter(getActivity(), getListOfData());
        recyclerView.setAdapter(customAdapter);

        return view;
    }
    private ArrayList<ItemModel> getListOfData() {
        ArrayList<ItemModel> listofData = new ArrayList<>();
        for(int i = 0; i< 10 ; i++) {
            listofData.add(new ItemModel(0, "", "", ""));

        }
        return listofData;
    }

}
