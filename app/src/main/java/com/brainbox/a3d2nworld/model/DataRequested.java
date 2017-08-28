/*
 * Created by Mong Ramos Jr. on 8/28/17 9:39 PM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 8/28/17 9:38 PM
 */

package com.brainbox.a3d2nworld.model;

import java.util.ArrayList;

public class DataRequested {

    //private CopyOnWriteArrayList<DealInfo> deals = new ArrayList<>(); //using threadsafe
    private ArrayList<DealInfo> deals = new ArrayList<>() ;
    private ArrayList<ResortInfo> resorts = new ArrayList<>();
    private int lastIdDeals = 0;
    private int lastIdResorts = 0;

    private static final DataRequested dataInstance = new DataRequested();

    public static DataRequested getInstance() {
        return dataInstance;
    }

    private DataRequested() {
    }

    //setter and getter
    public ArrayList<DealInfo> getDeals(){
        return deals;
    }

    public ArrayList<ResortInfo> getResorts(){
        return resorts;
    }

    public int getLastIdDeals(){
        return lastIdDeals;
    }
    public void setLastIdDeals(int lastIdDeals) { this.lastIdDeals = lastIdDeals;}

    public int getLastIdResorts(){
        return lastIdResorts;
    }
    public void setLastIdResorts(int lastIdResorts) {
        this.lastIdResorts = lastIdResorts;
    }
}
