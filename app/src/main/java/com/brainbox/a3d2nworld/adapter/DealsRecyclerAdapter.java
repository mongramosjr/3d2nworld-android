/*
 * Created by Mong Ramos Jr. on 8/28/17 9:39 PM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 8/28/17 9:38 PM
 */

package com.brainbox.a3d2nworld.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.util.TypedValue;

import com.brainbox.a3d2nworld.R;
import com.brainbox.a3d2nworld.model.DealInfo;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DealsRecyclerAdapter extends RecyclerView.Adapter<DealsRecyclerAdapter.DealViewHolder> {

    private Context mContext;
    private DealsAdapterListener listener;

    //private CopyOnWriteArrayList<DealInfo> dealList; //using threadsafe
    private ArrayList<DealInfo> dealList;

    public class DealViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{
        public TextView title, htmlDesc;
        public TextView amount;
        public ImageView thumbnail, addShoppingCart;
        RelativeLayout dealContainer;


        public DealViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.deal_title);
            amount = view.findViewById(R.id.deal_amount);
            thumbnail = view.findViewById(R.id.deal_thumbnail);
            addShoppingCart = view.findViewById(R.id.deal_add_shopping_cart);
            dealContainer = view.findViewById(R.id.deal_container);
            view.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {
            listener.onDealRowLongClicked(getAdapterPosition());
            view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
            return true;
        }
    }


    public DealsRecyclerAdapter(Context mContext, ArrayList<DealInfo> dealList, DealsAdapterListener listener) {
        this.mContext = mContext;
        this.dealList = dealList;
        this.listener = listener;
    }

    @Override
    public DealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.deals_content_card_view, parent, false);
        return new DealViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DealViewHolder holder, int position) {
        DealInfo deal = dealList.get(position);
        holder.title.setText(deal.getName());
        holder.amount.setText(String.valueOf(deal.getCurrency()) + String.valueOf(deal.getAmount()));

        // loading album cover using Glide library
        Glide.with(mContext).load(deal.getThumbnail()).into(holder.thumbnail);

        // apply click events
        applyClickEvents(holder, position);
    }

    @Override
    public int getItemCount() {
        return dealList.size();
    }

    private int dpToPx(int dp) {

        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, mContext.getResources().getDisplayMetrics()));
    }

    //Click Listener
    private void applyClickEvents(DealViewHolder holder, final int position) {
        holder.addShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onShoppingCartClicked(position);
            }
        });

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onThumbnailClicked(position);
            }
        });

        holder.dealContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDealRowClicked(position);
            }
        });

        holder.dealContainer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onDealRowLongClicked(position);
                view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                return true;
            }
        });
    }

    public interface DealsAdapterListener {
        void onShoppingCartClicked(int position);

        void onThumbnailClicked(int position);

        void onDealRowClicked(int position);

        void onDealRowLongClicked(int position);
    }


}
