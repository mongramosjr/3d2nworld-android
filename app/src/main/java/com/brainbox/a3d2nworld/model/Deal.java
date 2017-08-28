/*
 * Created by Mong Ramos Jr. on 8/28/17 9:39 PM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 8/28/17 9:38 PM
 */

package com.brainbox.a3d2nworld.model;


public class Deal {

    private int idx;
    private String name;
    private String htmlDesc;
    private String currency = "$";
    private float amount = 0.00f;
    private int thumbnail;
    private Resort resort;



    public Deal() {
    }

    public Deal(String name, float amount, int thumbnail) {
        this.name = name;
        this.amount = amount;
        this.thumbnail = thumbnail;
    }

    public Deal(String name, float amount, int thumbnail, Resort resort) {
        this.name = name;
        this.amount = amount;
        this.thumbnail = thumbnail;
        this.resort = resort;
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

    public void setHtmlDesc(String htmlDesc) {
        this.htmlDesc = htmlDesc;
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

    public Resort getResort() { return resort;}

    public void setResort(Resort resort) { this.resort = resort; }

    public String getCurrency(){return currency;}

    public void setCurrency(String currency) {this.currency = currency; }

    public int getIdx(){return idx; }
    void setIdx(int idx) {this.idx = idx; }

}
