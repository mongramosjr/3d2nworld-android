/*
 * Created by Mong Ramos Jr. on 12/10/17 6:15 PM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 12/10/17 6:09 PM
 */

package com.brainbox.a3d2nworld.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.brainbox.a3d2nworld.R;
import com.brainbox.a3d2nworld.activity.DealActivity;
import com.brainbox.a3d2nworld.adapter.DealsRecyclerAdapter;
import com.brainbox.a3d2nworld.model.DataRequested;
import com.brainbox.a3d2nworld.model.DealInfo;

import java.util.ArrayList;

public class DealsFragment extends Fragment implements DealsRecyclerAdapter.DealsAdapterListener {

    Context parentContext;
    ViewGroup parentViewGroup;
    View currentView;
	private RecyclerView recyclerView;
    private DealsRecyclerAdapter dealAdapter;
    private ArrayList<DealInfo> dealList;
    private SwipeRefreshLayout swipeRefreshLayout;

    
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation. 
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        parentContext = parent.getContext();
        parentViewGroup = parent;
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.main_fragment_deals_content, parent, false);
    }
	
    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        currentView = view;



        // Setup any handles to view objects here
        recyclerView = view.findViewById(R.id.main_fragment_deals_recycler_view);

        //dealList = new ArrayList<>();
        // get from singleton
        dealList = DataRequested.getInstance().getDeals();

        dealAdapter = new DealsRecyclerAdapter(view.getContext(), dealList, this);

        final int deals_grid_columns = getResources().getInteger(R.integer.deals_grid_columns);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(view.getContext(), deals_grid_columns, LinearLayoutManager.VERTICAL, false);

        //RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(dealAdapter);


        swipeRefreshLayout = view.findViewById(R.id.main_fragment_deals_swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                int lastIdDeals = DataRequested.getInstance().getLastIdDeals();
                refreshDeals(lastIdDeals);
            }
        });

        getDeals();
    }
    
	/**
     * Adding few albums for testing
     */
    private void getDeals() {

        swipeRefreshLayout.setRefreshing(true);

        //TODO: get from server after the last id
        // 1. if empty singleton, pull from the server, save to sqlite and singleton,
        // notify adapter
        // 2. if singleton is not empty and no network, dont notify adapter
        // 3. if singleton is not empty,pull from the server starting from the last id,
        // append to sqlite and singleton if has results, notify adapter.
        // otherwise dont notify adapter

        dealList = DataRequested.getInstance().getDeals();

        if(dealList.size() == 0 ){

            //TODO: replace this with data from server
            getSampleDeals();

            if(dealList.size()>0){
                dealAdapter.notifyDataSetChanged();
            }
        }else{


        }

        swipeRefreshLayout.setRefreshing(false);
    }

    public void refreshDeals(int last_insert_id){

        swipeRefreshLayout.setRefreshing(true);

        //TODO: get from server after the last id
        getAnotherSampleDeals(last_insert_id);

        dealAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    public void getSampleDeals(){

        int[] banners = new int[]{
                R.drawable.resort1,
                R.drawable.resort2,
                R.drawable.resort3,
                R.drawable.resort4};

        DealInfo deal = new DealInfo(1, "Henann Lagoon Resort", 1395);
        deal.setThumbnail(banners[0]);
        dealList.add(deal);

        deal = new DealInfo(2, "Henann Garden Resort", 8000);
        deal.setThumbnail(banners[1]);
        dealList.add(deal);

        deal = new DealInfo(3, "Boracay Tropics", 1100);
        deal.setThumbnail(banners[2]);
        dealList.add(deal);

        deal = new DealInfo(4, "Henann Regency Resort & Spa", 1200);
        deal.setThumbnail(banners[3]);
        dealList.add(deal);
        DataRequested.getInstance().setLastIdDeals(4);
    }

    public void getAnotherSampleDeals(int last_insert_id){

        int[] banners = new int[]{
                R.drawable.resort1,
                R.drawable.resort2,
                R.drawable.resort3,
                R.drawable.resort4};



        DealInfo deal = new DealInfo(last_insert_id+1, "Henann Lagoon Resort", 1395);
        deal.setThumbnail(banners[0]);
        dealList.add(0, deal);

        deal = new DealInfo(last_insert_id+2, "Henann Garden Resort", 8000);
        deal.setThumbnail(banners[1]);
        dealList.add(0, deal);

        deal = new DealInfo(last_insert_id+3, "Boracay Tropics", 1100);
        deal.setThumbnail(banners[1]);
        dealList.add(0, deal);

        deal = new DealInfo(last_insert_id+4, "Henann Regency Resort & Spa", 1200);
        deal.setThumbnail(banners[1]);
        dealList.add(0, deal);

        DataRequested.getInstance().setLastIdDeals(last_insert_id+4);
    }

    @Override
    public void onShoppingCartClicked(int position) {
        DealInfo deal = dealList.get(position);
        Snackbar.make(this.getView(), "onShoppingCartClicked: Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onThumbnailClicked(int position) {
        // Star icon is clicked,
        // mark the message as important
        DealInfo deal = dealList.get(position);
        Toast.makeText(this.getContext(), "onThumbnailClicked: " + deal.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDealRowClicked(int position) {
        DealInfo deal = dealList.get(position);

        Intent intent = new Intent(this.getContext(), DealActivity.class);

        //pass the nav drawer menu for deal
        intent.putExtra("main_fragment_id", R.id.drawer_navigation_deals);

        //intent.putParcelableArrayListExtra()
        //Bundle bundle = new Bundle();
        //bundle.putParcelableArrayList();

        ActivityOptionsCompat activityOptions;
        activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this.getActivity());

        //ActivityCompat.startActivity(this.getContext(), intent, activityOptions.toBundle());
        //startActivity(intent, activityOptions.toBundle());
        //ActivityCompat.startActivityForResult(this.getActivity(), intent, 0, activityOptions.toBundle());
        //startActivityForResult(intent, 200, activityOptions.toBundle());

        //-- correct way in calling startActivity in fragment:
        getActivity().startActivity(intent, activityOptions.toBundle());


    }

    @Override
    public void onDealRowLongClicked(int position) {
        // long press is performed, enable action mode
        DealInfo deal = dealList.get(position);
        Toast.makeText(this.getContext(), "onDealRowLongClicked: " + deal.getName(), Toast.LENGTH_SHORT).show();
    }

}
