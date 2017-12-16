/*
 * Created by Mong Ramos Jr. on 12/17/17 7:45 AM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 12/17/17 7:40 AM
 */

package com.basement720.odoo.http.message;

import org.json.JSONObject;

/**
 * Created by mong on 10/8/17.
 */

public interface JSONObjectInterface {

    JSONObject getContents();

    void setContents(JSONObject contents);

    void setContents(String contents);

}
