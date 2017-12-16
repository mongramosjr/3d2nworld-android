/*
 * Created by Mong Ramos Jr. on 12/17/17 7:45 AM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 12/17/17 7:44 AM
 */

package com.brainbox.a3d2nworld.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;


import com.brainbox.a3d2nworld.R;
import com.brainbox.a3d2nworld.adapter.DealsRecyclerAdapter;
import com.brainbox.a3d2nworld.base.BaseDrawerActivity;
import com.brainbox.a3d2nworld.model.DealInfo;

import java.util.ArrayList;

public class DealsActivity extends BaseDrawerActivity implements DealsRecyclerAdapter.DealsAdapterListener {

    private RecyclerView recyclerView;
    private DealsRecyclerAdapter dealAdapter;
    private ArrayList<DealInfo> dealList;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //content
        setContentView(R.layout.deals_activity);

        //include
        // inflate content layout and add it to the relative layout as first child
        // add as first child, therefore pass index 1 (0,1,...)

        //CoordinatorLayout coordinatorLayout = (CoordinatorLayout) View.inflate(this,
        //        R.layout.deals_main_view, null);
        //drawerLayout.addView(coordinatorLayout, 0);

        //toolbar
        toolbar = findViewById(R.id.home_toolbar);
        toolbar.setLogo(R.drawable.toolbar_3d2n);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //drawer layout
        drawerLayout = findViewById(R.id.deals_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        //navigation view
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = findViewById(R.id.deals_recycler_view);
        dealList = new ArrayList<>();
        dealAdapter = new DealsRecyclerAdapter(this, dealList, this);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL, false);
        //RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(dealAdapter);


        swipeRefreshLayout = findViewById(R.id.deals_swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                prepareDeals();
            }
        });


        navigationView.getMenu().findItem(R.id.drawer_navigation_deals);
        enabledMenuItemSelected(getIntent());

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

        DealInfo deal = new DealInfo(1, "Henann Lagoon Resort", 1395);
        deal.setThumbnail(banners[0]);
        dealList.add(deal);

        deal = new DealInfo(2, "Henann Garden Resort", 8080);
        deal.setThumbnail(banners[1]);
        dealList.add(deal);

        deal = new DealInfo(3, "Boracay Tropics", 1180);
        deal.setThumbnail(banners[2]);
        dealList.add(deal);

        deal = new DealInfo(4, "Henann Regency Resort & Spa", 1280);
        deal.setThumbnail(banners[3]);
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



    //Click
    @Override
    public void onShoppingCartClicked(int position) {
        DealInfo deal = dealList.get(position);
        Toast.makeText(this.getBaseContext(), "Read: IconClicked " + deal.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onThumbnailClicked(int position) {
        DealInfo deal = dealList.get(position);
        Toast.makeText(this.getBaseContext(), "Read: IconImportantClicked " + deal.getName(), Toast.LENGTH_SHORT).show();
        Intent intent;
        ActivityOptionsCompat activityOptions;
        intent = new Intent(this, DealActivity.class);
        activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this);

        if(intent != null && activityOptions != null) {
            startActivity(intent, activityOptions.toBundle());
        }
    }

    @Override
    public void onDealRowClicked(int position) {
        DealInfo deal = dealList.get(position);
        Toast.makeText(this.getBaseContext(), "Read: RowClicked " + deal.getName(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDealRowLongClicked(int position) {
        DealInfo deal = dealList.get(position);
        Toast.makeText(this.getBaseContext(), "Read: LongClicked " + deal.getName(), Toast.LENGTH_SHORT).show();
    }

}
