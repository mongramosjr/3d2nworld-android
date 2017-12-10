/*
 * Created by Mong Ramos Jr. on 12/10/17 6:15 PM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 12/10/17 6:09 PM
 */

package com.brainbox.a3d2nworld.fragment;

import android.support.annotation.NonNull;
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
import com.brainbox.a3d2nworld.model.DataRequested;
import com.brainbox.a3d2nworld.model.DealInfo;
import com.brainbox.a3d2nworld.model.Resort;
import com.brainbox.a3d2nworld.model.ResortInfo;

import java.util.ArrayList;
import java.util.List;

public class ResortsFragment extends Fragment implements ResortsRecyclerAdapter.ResortsAdapterListener {
	
    private RecyclerView recyclerView;
    private ResortsRecyclerAdapter resortAdapter;
    private ArrayList<ResortInfo> resortList;
    private SwipeRefreshLayout swipeRefreshLayout;
    
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation. 
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.main_fragment_resorts_content, parent, false);
    }
	
    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        recyclerView = view.findViewById(R.id.main_fragment_resorts_recycler_view);


        //resortList = new ArrayList<>();
        // get from singleton
        resortList = DataRequested.getInstance().getResorts();

        resortAdapter = new ResortsRecyclerAdapter(view.getContext(), resortList, this);

        final int resorts_grid_columns = getResources().getInteger(R.integer.resorts_grid_columns);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(view.getContext(), resorts_grid_columns, LinearLayoutManager.HORIZONTAL, false);
        //RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(resortAdapter);


        swipeRefreshLayout = view.findViewById(R.id.main_fragment_resorts_swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                int lastIdResorts = DataRequested.getInstance().getLastIdResorts();
                refreshResorts(lastIdResorts);
            }
        });

        getResorts();
    }

    private void getResorts() {

        swipeRefreshLayout.setRefreshing(true);

        //TODO: get from server after the last id
        // 1. if empty singleton, pull from the server, save to sqlite and singleton,
        // notify adapter
        // 2. if singleton is not empty and no network, dont notify adapter
        // 3. if singleton is not empty,pull from the server starting from the last id,
        // append to sqlite and singleton if has results, notify adapter.
        // otherwise dont notify adapter

        resortList = DataRequested.getInstance().getResorts();

        if(resortList.size() == 0 ){

            //TODO: replace this with data from server
            getSampleResorts();

            if(resortList.size()>0){
                resortAdapter.notifyDataSetChanged();
            }
        }else{


        }

        swipeRefreshLayout.setRefreshing(false);
    }

    public void refreshResorts(int last_insert_id){

        swipeRefreshLayout.setRefreshing(true);

        //TODO: get from server after the last id
        getAnotherSampleResorts(last_insert_id);

        resortAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }
    
	/**
     * Adding few albums for testing
     */
    private void getSampleResorts() {

        int[] banners = new int[]{
                R.drawable.resort1,
                R.drawable.resort2,
                R.drawable.resort3,
                R.drawable.resort4};

        ResortInfo resort = new ResortInfo(1, "Henann Lagoon Resort", "Come and take pleasure on a private escape right in the heart of Boracay Island. Experience Boracay in luxury and comfort at Henann Lagoon Resort.", 13);
        resort.setThumbnail(banners[0]);
        resortList.add(resort);

        resort = new ResortInfo(2, "Henann Garden Resort", "Luxurious, affordable accommodations without compromise.", 8);
        resort.setThumbnail(banners[1]);
        resortList.add(resort);

        resort = new ResortInfo(3, "Boracay Tropics", "Boracay Tropics gives you privacy when you need it, party energy at your fingertips.", 11);
        resort.setThumbnail(banners[2]);
        resortList.add(resort);

        resort = new ResortInfo(4, "Henann Regency Resort & Spa", "Experience the crystal clear waters and powder-white sand of the island like never before with Henann Regency.",12);
        resort.setThumbnail(banners[3]);
        resortList.add(resort);

        DataRequested.getInstance().setLastIdDeals(4);

    }

    public void getAnotherSampleResorts(int last_insert_id){

        int[] banners = new int[]{
                R.drawable.resort1,
                R.drawable.resort2,
                R.drawable.resort3,
                R.drawable.resort4};



        ResortInfo resort = new ResortInfo(1, "Henann Lagoon Resort", "Come and take pleasure on a private escape right in the heart of Boracay Island. Experience Boracay in luxury and comfort at Henann Lagoon Resort.", 13);
        resort.setThumbnail(banners[0]);
        resortList.add(0, resort);

        resort = new ResortInfo(2, "Henann Garden Resort", "Luxurious, affordable accommodations without compromise.", 8);
        resort.setThumbnail(banners[1]);
        resortList.add(0, resort);

        resort = new ResortInfo(3, "Boracay Tropics", "Boracay Tropics gives you privacy when you need it, party energy at your fingertips.", 11);
        resort.setThumbnail(banners[2]);
        resortList.add(0, resort);

        resort = new ResortInfo(4, "Henann Regency Resort & Spa", "Experience the crystal clear waters and powder-white sand of the island like never before with Henann Regency.",12);
        resort.setThumbnail(banners[3]);
        resortList.add(0, resort);

        DataRequested.getInstance().setLastIdResorts(last_insert_id+4);
    }

    @Override
    public void onThumbnailClicked(int position) {

    }
}
