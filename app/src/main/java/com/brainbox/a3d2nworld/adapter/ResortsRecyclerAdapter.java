/*
 * Created by Mong Ramos Jr. on 8/26/17 11:45 AM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 8/26/17 11:43 AM
 */

package com.brainbox.a3d2nworld.adapter;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.util.TypedValue;

import com.brainbox.a3d2nworld.R;
import com.brainbox.a3d2nworld.model.Resort;
import com.bumptech.glide.Glide;
import java.util.List;

public class ResortsRecyclerAdapter extends RecyclerView.Adapter<ResortsRecyclerAdapter.ResortViewHolder> {

    private Context mContext;
    private List<Resort> resortList;

    public class ResortViewHolder extends RecyclerView.ViewHolder {
        public TextView title, shortDesc;
        public TextView likes;
        public TextView amount;
        public ImageView thumbnail;

        private int resort_label_height;
        //public ImageView resortMenu;

        public ResortViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            shortDesc = view.findViewById(R.id.shortDesc);
            amount = view.findViewById(R.id.amount);
            likes = view.findViewById(R.id.likes);
            thumbnail = view.findViewById(R.id.thumbnail);

            resort_label_height = view.findViewById(R.id.resort_label_view).getLayoutParams().height;
        }
    }


    public ResortsRecyclerAdapter(Context mContext, List<Resort> resortList) {
        this.mContext = mContext;
        this.resortList = resortList;
    }

    @Override
    public ResortViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_content_card_fullscreen, parent, false);
        return new ResortViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ResortViewHolder holder, int position) {
        Resort resort = resortList.get(position);
        holder.title.setText(resort.getName());
        holder.shortDesc.setText(resort.getShortDesc());
        holder.likes.setText(String.valueOf(resort.getLikes()));
        holder.amount.setText(String.valueOf(resort.getAmount()));



        int height = mContext.getResources().getDisplayMetrics().heightPixels;
        holder.title.setText(java.lang.String.valueOf(holder.resort_label_height));
        holder.thumbnail.getLayoutParams().height = height  - dpToPx(holder.resort_label_height);
        holder.thumbnail.requestLayout();

        // loading album cover using Glide library
        Glide.with(mContext).load(resort.getThumbnail()).into(holder.thumbnail);

        //holder.resortMenu.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        showPopupMenu(holder.resortMenu);
        //    }
        //});
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        //PopupMenu popup = new PopupMenu(mContext, view);
        //MenuInflater inflater = popup.getMenuInflater();
        //inflater.inflate(R.menu.menu_resort, popup.getMenu());
        //popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        //popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                //case R.id.action_add_favourite:
                //    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                //    return true;
                //case R.id.action_play_next:
                //    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                //    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return resortList.size();
    }

    private int dpToPx(int dp) {

        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, mContext.getResources().getDisplayMetrics()));
    }
}
