/*
 * Created by Mong Ramos Jr. on 8/28/17 9:36 PM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 8/28/17 11:56 AM
 */

package com.brainbox.a3d2nworld.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ResortInfo implements Parcelable {

    private String name;
    private String htmlDesc;
    private int likes = 0;
    private int stars = 0;
    private int idx;

    private int thumbnail;

    public ResortInfo(){

    }

    public ResortInfo(int idx, String name, String htmlDesc, int stars){
        this.idx = idx;
        this.name = name;
        this.htmlDesc = htmlDesc;
        this.stars  = stars;
    }


    // "De-parcel object
    public ResortInfo(Parcel in) {
        idx = in.readInt();
        name = in.readString();
        htmlDesc = in.readString();
        stars = in.readInt();

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
        parcel.writeInt(likes);
        parcel.writeInt(stars);
    }

    // Creator
    //public static final Parcelable.Creator CREATOR
    //        = new Parcelable.Creator() {
    public static final Creator<ResortInfo> CREATOR = new Creator<ResortInfo>() {
        @Override
        public ResortInfo createFromParcel(Parcel parcel) {
            return new ResortInfo(parcel);
        }

        @Override
        public ResortInfo[] newArray(int size) {
            return new ResortInfo[size];
        }
    };

    public int getIdx(){return idx; }
    void setIdx(int idx) {this.idx = idx; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getHtmlDesc() { return htmlDesc; }
    public void setHtmlDesc(String htmlDesc) { this.htmlDesc = htmlDesc;}

    public int getLikes() { return likes; }
    public void setLikes(int likes) { this.likes = likes; }

    public int getStars() { return stars; }
    public void setStars(int stars) { this.stars = stars; }

    public int getThumbnail() { return thumbnail; }
    public void setThumbnail(int thumbnail) { this.thumbnail = thumbnail; }



}
