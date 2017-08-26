/*
 * Created by Mong Ramos Jr. on 8/26/17 11:45 AM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 8/26/17 10:19 AM
 */

package com.brainbox.a3d2nworld.model;

public class Resort {

    private String name;
    private String shortDesc;
    private float amount = 0.00f;
    private int thumbnail;
    private int likes = 0;
    private int stars = 0;

    public Resort() {
    }

    public Resort(String name, String shortDesc, int likes, int thumbnail) {
        this.name = name;
        this.likes = likes;
        this.shortDesc = shortDesc;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDeschortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
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
}
