/*
 * Created by Mong Ramos Jr. on 8/27/17 4:55 PM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 8/27/17 4:54 PM
 */

package com.brainbox.a3d2nworld.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brainbox.a3d2nworld.R;
import com.brainbox.a3d2nworld.adapter.ResortsRecyclerAdapter;
import com.brainbox.a3d2nworld.model.Resort;

import java.util.ArrayList;
import java.util.List;

public class ResortsFragment extends Fragment {
	
    private RecyclerView recyclerView;
    private ResortsRecyclerAdapter resortAdapter;
    private List<Resort> resortList;
    private SwipeRefreshLayout swipeRefreshLayout;
    
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation. 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.main_resorts_content, parent, false);
    }
	
    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        recyclerView = view.findViewById(R.id.resorts_recycler_view);
        resortList = new ArrayList<>();
        resortAdapter = new ResortsRecyclerAdapter(view.getContext(), resortList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(view.getContext(),1, LinearLayoutManager.HORIZONTAL, false);
        //RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(resortAdapter);


        swipeRefreshLayout = view.findViewById(R.id.resorts_swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                prepareResorts();
            }
        });

        prepareResorts();
    }
    
	/**
     * Adding few albums for testing
     */
    private void prepareResorts() {

        swipeRefreshLayout.setRefreshing(true);

        int[] banners = new int[]{
                R.drawable.resort1,
                R.drawable.resort2,
                R.drawable.resort3,
                R.drawable.resort4};

        Resort resort = new Resort("Henann Lagoon Resort", "Come and take pleasure on a private escape right in the heart of Boracay Island. Experience Boracay in luxury and comfort at Henann Lagoon Resort.", 13, banners[0]);
        resortList.add(resort);

        resort = new Resort("Henann Garden Resort", "Luxurious, affordable accommodations without compromise.", 8, banners[1]);
        resortList.add(resort);

        resort = new Resort("Boracay Tropics", "Boracay Tropics gives you privacy when you need it, party energy at your fingertips.", 11, banners[2]);
        resortList.add(resort);

        resort = new Resort("Henann Regency Resort & Spa", "Experience the crystal clear waters and powder-white sand of the island like never before with Henann Regency.",12, banners[3]);
        resortList.add(resort);

        resortAdapter.notifyDataSetChanged();

        swipeRefreshLayout.setRefreshing(false);
    }
}
