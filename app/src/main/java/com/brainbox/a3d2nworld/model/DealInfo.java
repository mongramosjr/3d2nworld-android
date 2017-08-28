/*
 * Created by Mong Ramos Jr. on 8/28/17 9:36 PM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 8/28/17 12:07 PM
 */

package com.brainbox.a3d2nworld.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DealInfo implements Parcelable {

    private int idx;
    private String name;
    private String htmlDesc;
    private String currency = "$";
    private float amount = 0.00f;

    private ResortInfo resortInfo;

    private int thumbnail;


    public DealInfo(){

    }

    public DealInfo(int idx, String name, float amount){
        this.idx = idx;
        this.name = name;
        this.amount  = amount;
    }

    public DealInfo(int idx, String name, String htmlDesc,float amount){
        this.idx = idx;
        this.name = name;
        this.htmlDesc = htmlDesc;
        this.amount  = amount;
    }


    // "De-parcel object
    public DealInfo(Parcel in) {
        idx = in.readInt();
        name = in.readString();
        htmlDesc = in.readString();
        amount = in.readFloat();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idx);
        parcel.writeString(name);
        parcel.writeString(htmlDesc);
        parcel.writeFloat(amount);
    }

    // Creator
    //public static final Parcelable.Creator CREATOR
    //        = new Parcelable.Creator() {
    public static final Creator<DealInfo> CREATOR = new Creator<DealInfo>() {
        @Override
        public DealInfo createFromParcel(Parcel parcel) {
            return new DealInfo(parcel);
        }

        @Override
        public DealInfo[] newArray(int size) {
            return new DealInfo[size];
        }
    };

    //setter and getter
    public int getIdx(){return idx; }
    void setIdx(int idx) {this.idx = idx; }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getHtmlDesc() {
        return htmlDesc;
    }
    public void setHtmlDesc(String htmlDesc) {
        this.htmlDesc = htmlDesc;
    }

    public float getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ResortInfo getResort() { return resortInfo;}
    public void setResort(ResortInfo resortInfo) { this.resortInfo = resortInfo; }

    public String getCurrency(){return currency;}
    public void setCurrency(String currency) {this.currency = currency; }

    public int getThumbnail() {
        return thumbnail;
    }
    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }




}
