/*
 * Created by Mong Ramos Jr. on 8/28/17 9:36 PM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 8/28/17 12:11 PM
 */

package com.brainbox.a3d2nworld.activity;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import com.brainbox.a3d2nworld.R;
import com.brainbox.a3d2nworld.adapter.ResortsRecyclerAdapter;
import com.brainbox.a3d2nworld.base.BaseDrawerActivity;
import com.brainbox.a3d2nworld.model.Resort;
import com.brainbox.a3d2nworld.model.ResortInfo;

import java.util.ArrayList;
import java.util.List;

public class ResortsActivity extends BaseDrawerActivity {


    private RecyclerView recyclerView;
    private ResortsRecyclerAdapter resortAdapter;
    private ArrayList<ResortInfo> resortList;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.resorts_activity);

        //include
        // inflate content layout and add it to the relative layout as first child
        // add as first child, therefore pass index 1 (0,1,...)

        //CoordinatorLayout coordinatorLayout = (CoordinatorLayout) View.inflate(this,
        //        R.layout.resorts_main_view, null);
        //drawerLayout.addView(coordinatorLayout, 0);

        //toolbar
        toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        toolbar.setLogo(R.drawable.toolbar_3d2n);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //drawer layout
        drawerLayout = (DrawerLayout) findViewById(R.id.resorts_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        //navigation view
        NavigationView navigationView = (NavigationView) findViewById(R.id.home_nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.deal_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.resorts_recycler_view);
        resortList = new ArrayList<>();
        resortAdapter = new ResortsRecyclerAdapter(this, resortList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this,1, LinearLayoutManager.HORIZONTAL, false);
        //RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(resortAdapter);


        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.resorts_swipe_refresh_layout);
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

        resortAdapter.notifyDataSetChanged();

        swipeRefreshLayout.setRefreshing(false);
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
