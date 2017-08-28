/*
 * Created by Mong Ramos Jr. on 8/28/17 9:36 PM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 8/28/17 12:56 PM
 */

package com.brainbox.a3d2nworld.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

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
        toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        toolbar.setLogo(R.drawable.toolbar_3d2n);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //drawer layout
        drawerLayout = (DrawerLayout) findViewById(R.id.main_fragment_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        //navigation view
        NavigationView navigationView = (NavigationView) findViewById(R.id.home_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
}
