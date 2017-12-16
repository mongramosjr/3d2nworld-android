/*
 * Created by Mong Ramos Jr. on 12/17/17 7:45 AM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 12/17/17 7:40 AM
 */

package com.basement720.odoo.http.message;

import com.basement720.odoo.http.client.OdooResponse;

public class SyncResponse{

    public OdooResponse response;

    public OdooResponse getResponse() {
        return response;
    }

    public void setResponse(OdooResponse response) {
        this.response = response;
    }
}
