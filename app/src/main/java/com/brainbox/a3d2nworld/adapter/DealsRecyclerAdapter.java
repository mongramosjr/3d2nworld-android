/*
 * Created by Mong Ramos Jr. on 8/26/17 9:33 PM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 8/26/17 3:38 PM
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
import com.brainbox.a3d2nworld.model.Deal;
import com.bumptech.glide.Glide;
import java.util.List;

public class DealsRecyclerAdapter extends RecyclerView.Adapter<DealsRecyclerAdapter.DealViewHolder> {

    private Context mContext;
    private List<Deal> dealList;

    public class DealViewHolder extends RecyclerView.ViewHolder {
        public TextView title, htmlDesc;
        public TextView amount;
        public ImageView thumbnail;


        public DealViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.deal_title);
            amount = view.findViewById(R.id.deal_amount);
            thumbnail = view.findViewById(R.id.deal_thumbnail);

        }
    }


    public DealsRecyclerAdapter(Context mContext, List<Deal> dealList) {
        this.mContext = mContext;
        this.dealList = dealList;
    }

    @Override
    public DealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.deals_content_card_view, parent, false);
        return new DealViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DealViewHolder holder, int position) {
        Deal deal = dealList.get(position);
        holder.title.setText(deal.getName());
        holder.amount.setText(String.valueOf(deal.getAmount()));

        // loading album cover using Glide library
        Glide.with(mContext).load(deal.getThumbnail()).into(holder.thumbnail);

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
        return dealList.size();
    }

    private int dpToPx(int dp) {

        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, mContext.getResources().getDisplayMetrics()));
    }

}
