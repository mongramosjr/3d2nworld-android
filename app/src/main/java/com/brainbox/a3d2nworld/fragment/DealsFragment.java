/*
 * Created by Mong Ramos Jr. on 8/27/17 4:55 PM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 8/27/17 4:54 PM
 */

package com.brainbox.a3d2nworld.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
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
import com.brainbox.a3d2nworld.activity.ResortsActivity;
import com.brainbox.a3d2nworld.adapter.DealsRecyclerAdapter;
import com.brainbox.a3d2nworld.model.Deal;

import java.util.ArrayList;
import java.util.List;

public class DealsFragment extends Fragment implements DealsRecyclerAdapter.DealsAdapterListener {

    Context parentContext;
    ViewGroup parentViewGroup;
    View currentView;
	private RecyclerView recyclerView;
    private DealsRecyclerAdapter dealAdapter;
    private List<Deal> dealList;
    private SwipeRefreshLayout swipeRefreshLayout;

    
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation. 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        parentContext = parent.getContext();
        parentViewGroup = parent;
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.main_deals_content, parent, false);
    }
	
    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        currentView = view;
        // Setup any handles to view objects here
        recyclerView = view.findViewById(R.id.deals_recycler_view);
        dealList = new ArrayList<>();
        dealAdapter = new DealsRecyclerAdapter(view.getContext(), dealList, this);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(view.getContext(),1, LinearLayoutManager.VERTICAL, false);
        //RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(dealAdapter);


        swipeRefreshLayout = view.findViewById(R.id.deals_swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                prepareDeals();
            }
        });

        prepareDeals();
    }
    
	/**
     * Adding few albums for testing
     */
    private void prepareDeals() {

        swipeRefreshLayout.setRefreshing(true);

        int[] banners = new int[]{
                R.drawable.resort1,
                R.drawable.resort2,
                R.drawable.resort3,
                R.drawable.resort4};

        Deal deal = new Deal("Henann Lagoon Resort", 13, banners[0]);
        dealList.add(deal);

        deal = new Deal("Henann Garden Resort", 8, banners[1]);
        dealList.add(deal);

        deal = new Deal("Boracay Tropics", 11, banners[2]);
        dealList.add(deal);

        deal = new Deal("Henann Regency Resort & Spa", 12, banners[3]);
        dealList.add(deal);

        dealAdapter.notifyDataSetChanged();

        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onShoppingCartClicked(int position) {
        Deal deal = dealList.get(position);
        Snackbar.make(this.getView(), "onShoppingCartClicked: Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onThumbnailClicked(int position) {
        // Star icon is clicked,
        // mark the message as important
        Deal deal = dealList.get(position);
        Toast.makeText(this.getContext(), "onThumbnailClicked: " + deal.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDealRowClicked(int position) {
        Deal deal = dealList.get(position);

        Intent intent = new Intent(this.getContext(), DealActivity.class);
        ActivityOptionsCompat activityOptions;
        activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this.getActivity());
        ActivityCompat.startActivity(this.getContext(), intent, activityOptions.toBundle());


    }

    @Override
    public void onDealRowLongClicked(int position) {
        // long press is performed, enable action mode
        Deal deal = dealList.get(position);
        Toast.makeText(this.getContext(), "onDealRowLongClicked: " + deal.getName(), Toast.LENGTH_SHORT).show();
    }

}
