/*
 * Created by Mong Ramos Jr. on 12/17/17 7:45 AM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 12/17/17 7:44 AM
 */

package com.brainbox.a3d2nworld.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.brainbox.a3d2nworld.R;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        final TextView link_login = findViewById(R.id.link_login);

        //requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setProgressBarIndeterminateVisibility(true);

        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewIn) {

                log_in();
            }
        });
    }

    private void log_in()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this);

        if(intent != null && activityOptions != null) {
            //intent
            intent.putExtra("selected_menu_id", R.id.drawer_navigation_login);
            //start activity
            startActivity(intent, activityOptions.toBundle());
        }
        return;
    }
}
