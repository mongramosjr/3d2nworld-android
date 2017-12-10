/*
 * Created by Mong Ramos Jr. on 12/10/17 6:15 PM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 12/8/17 12:05 PM
 */

package com.brainbox.a3d2nworld.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;

import com.brainbox.a3d2nworld.R;
import com.brainbox.a3d2nworld.base.BaseDrawerFragmentActivity;
import com.brainbox.a3d2nworld.model.DataRequested;
import com.brainbox.a3d2nworld.model.DealInfo;
import com.brainbox.a3d2nworld.model.ResortInfo;

import java.util.ArrayList;

public class MainFragmentActivity extends BaseDrawerFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fragment_activity);

        ArrayList<DealInfo> deals = DataRequested.getInstance().getDeals();
        ArrayList<ResortInfo> resorts = DataRequested.getInstance().getResorts();

        //toolbar
        toolbar = findViewById(R.id.home_toolbar);
        toolbar.setLogo(R.drawable.toolbar_3d2n);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //drawer layout
        drawerLayout = findViewById(R.id.main_fragment_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        //navigation view
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        //check if there is intent to view the fragment, otherwise show the resorts
        int nav = getIntent().getIntExtra("main_fragment_id", R.id.drawer_navigation_resorts);
        onNavigationItemSelected(navigationView.getMenu().findItem(nav));
        if(nav == R.id.drawer_navigation_resorts){
            navigationView.setCheckedItem(R.id.drawer_navigation_resorts);
        }else if(nav == R.id.drawer_navigation_deals){
            navigationView.setCheckedItem(R.id.drawer_navigation_blogs);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
