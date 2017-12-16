/*
 * Created by Mong Ramos Jr. on 12/17/17 7:45 AM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 12/17/17 7:40 AM
 */

package com.basement720.odoo.http.message;

import com.basement720.odoo.log.OdooLog;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


/**
 * Created by mong on 10/8/17.
 */

public class MessageJson implements JSONObjectInterface {

    JSONObject contents;

    @Override
    public JSONObject getContents() {
        return contents;
    }

    @Override
    public void setContents(JSONObject contents) {
        this.contents = contents;
    }

    @Override
    public void setContents(String json) {
        try {
            JSONObject object = (JSONObject) new JSONTokener(json).nextValue();
            this.contents = object;
        } catch (JSONException e) {
            OdooLog.e(e.getMessage());
        }
    }

    public String decode(){
        return contents.toString();
    }

}
