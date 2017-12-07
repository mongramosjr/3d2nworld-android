/*
 * Created by Mong Ramos Jr. on 12/7/17 11:47 AM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 10/8/17 1:31 PM
 */

package com.fourvelocity.odoo.http.message;

import org.json.JSONObject;

/**
 * Created by mong on 10/8/17.
 */

public interface JSONObjectInterface {

    JSONObject getContents();

    void setContents(JSONObject contents);

    void setContents(String contents);

}
