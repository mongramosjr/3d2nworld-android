/*
 * Created by Mong Ramos Jr. on 8/27/17 4:55 PM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 8/27/17 10:14 AM
 */

package com.brainbox.a3d2nworld.base;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;


import com.brainbox.a3d2nworld.R;
import com.brainbox.a3d2nworld.activity.DealsActivity;
import com.brainbox.a3d2nworld.activity.ResortsActivity;

@SuppressLint("Registered")
public class BaseDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{


    public DrawerLayout drawerLayout;
    public NavigationView navigationView;
    public Toolbar toolbar;

    public BaseDrawerActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //content
        //super.setContentView(R.layout.base_drawer_activity);

        //toolbar
        //toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        //toolbar.setLogo(R.drawable.toolbar_3d2n);
        //toolbar.setTitle("");
        //setSupportActionBar(toolbar);

        //drawer layout
        //drawerLayout = (DrawerLayout) findViewById(R.id.base3d2n_drawer_layout);
        //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        //        this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawerLayout.setDrawerListener(toggle);
        //toggle.syncState();

        //navigation view
        //NavigationView navigationView = (NavigationView) findViewById(R.id.home_nav_view);
        //navigationView.setNavigationItemSelectedListener(this);


    }


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cart) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        //-- using Activity
        Intent intent = new Intent(this, DealsActivity.class);
        ActivityOptionsCompat activityOptions = null;

        //to prevent current item select over and over
        if (item.isChecked()) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return false;
        }




        switch(id) {
            case R.id.nav_resorts:
                //-- using Activity
                intent = new Intent(this, ResortsActivity.class);
                activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
                break;

            case R.id.nav_deals:
                intent = new Intent(this, DealsActivity.class);
                activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
                break;

            case R.id.nav_newsletter:

                break;

            default:

        }

        // Highlight the selected item has been done by NavigationView
        item.setChecked(true);
        // Set action bar title
        setTitle(item.getTitle());
        // Close the navigation drawer
        drawerLayout.closeDrawer(GravityCompat.START);

        //-- using Activity
        ActivityCompat.startActivity(this, intent, activityOptions.toBundle());

        return true;
    }
}
