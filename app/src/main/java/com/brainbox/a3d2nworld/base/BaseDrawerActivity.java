/*
 * Created by Mong Ramos Jr. on 12/17/17 7:45 AM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 12/10/17 8:55 PM
 */

package com.brainbox.a3d2nworld.base;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;


import com.brainbox.a3d2nworld.R;
import com.brainbox.a3d2nworld.activity.BlogsActivity;
import com.brainbox.a3d2nworld.activity.DealsActivity;
import com.brainbox.a3d2nworld.activity.LoginActivity;
import com.brainbox.a3d2nworld.activity.ProfileActivity;
import com.brainbox.a3d2nworld.activity.ResortsActivity;
import com.brainbox.a3d2nworld.activity.VouchersActivity;

@SuppressLint("Registered")
public class BaseDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    public android.support.v7.app.ActionBar mActionBar;

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle drawerToggle;

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
        //toolbar = (Toolbar) findViewById(R.id.toolbar);
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

    public void enabledMenuItemSelected(Intent intent)
    {
        int id = intent.getIntExtra("selected_menu_id", 0);

        if(navigationView == null ) return;

        MenuItem item = navigationView.getMenu().findItem(id);
        if(item != null){
            item.setChecked(true);
        }
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
        getMenuInflater().inflate(R.menu.toolbar, menu);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        //-- using Activity
        Intent intent = null;
        ActivityOptionsCompat activityOptions = null;

        //to prevent current item select over and over
        if (item.isChecked()) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return false;
        }




        switch(id) {
            case R.id.drawer_navigation_resorts:
                //-- using Activity
                intent = new Intent(this, ResortsActivity.class);
                activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
                break;

            case R.id.drawer_navigation_deals:
                intent = new Intent(this, DealsActivity.class);
                activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
                break;

            case R.id.drawer_navigation_blogs:
                intent = new Intent(this, BlogsActivity.class);
                activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this);

                break;

            case R.id.drawer_navigation_profile:
                intent = new Intent(this, ProfileActivity.class);
                activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this);

                break;

            case R.id.drawer_navigation_vouchers:
                intent = new Intent(this, VouchersActivity.class);
                activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this);


                break;

            case R.id.drawer_navigation_logout:

                break;

            case R.id.drawer_navigation_login:
                intent = new Intent(this, LoginActivity.class);
                activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this);

                break;

            default:

        }

        // Highlight the selected item has been done by NavigationView
        item.setChecked(true);
        // Set action bar title
        setTitle(item.getTitle());
        // Close the navigation drawer
        drawerLayout.closeDrawer(GravityCompat.START);

        if(intent != null && activityOptions != null) {
            //intent
            intent.putExtra("selected_menu_id", id);
            //start activity
            startActivity(intent, activityOptions.toBundle());
        }

        return true;
    }
}
