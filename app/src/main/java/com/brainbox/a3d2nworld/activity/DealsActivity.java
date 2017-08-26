/*
 * Created by Mong Ramos Jr. on 8/26/17 9:33 PM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 8/26/17 9:26 PM
 */

package com.brainbox.a3d2nworld.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.view.LayoutInflater;


import com.brainbox.a3d2nworld.R;
import com.brainbox.a3d2nworld.adapter.DealsRecyclerAdapter;
import com.brainbox.a3d2nworld.model.Deal;

import java.util.ArrayList;
import java.util.List;

public class DealsActivity extends BaseDrawerActivity {

    private RecyclerView recyclerView;
    private DealsRecyclerAdapter dealAdapter;
    private List<Deal> dealList;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //content
        //setContentView(R.layout.base_drawer_activity);

        //include
        // inflate content layout and add it to the relative layout as first child
        // add as first child, therefore pass index 1 (0,1,...)

        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) View.inflate(this,
                R.layout.deals_main_view, null);
        drawerLayout.addView(coordinatorLayout, 0);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        toolbar.setLogo(R.drawable.toolbar_3d2n);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        recyclerView = (RecyclerView) findViewById(R.id.deals_recycler_view);
        dealList = new ArrayList<>();
        dealAdapter = new DealsRecyclerAdapter(this, dealList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL, false);
        //RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(dealAdapter);


        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.deals_swipe_refresh_layout);
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
