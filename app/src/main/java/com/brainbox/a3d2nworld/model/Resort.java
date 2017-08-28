/*
 * Created by Mong Ramos Jr. on 8/28/17 9:36 PM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 8/28/17 11:53 AM
 */

package com.brainbox.a3d2nworld.model;

public class Resort {

    private String name;
    private String htmlDesc;
    private float amount = 0.00f;
    private int thumbnail;
    private int likes = 0;
    private int stars = 0;
    private int idx;

    public Resort() {
    }

    public Resort(String name, String shortDesc, int likes, int thumbnail) {
        this.name = name;
        this.likes = likes;
        this.htmlDesc = shortDesc;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtmlDesc() {
        return htmlDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.htmlDesc = shortDesc;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getIdx(){return idx; }
    void setIdx(int idx) {this.idx = idx; }
}
