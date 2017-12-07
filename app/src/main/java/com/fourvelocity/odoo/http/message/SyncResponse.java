/*
 * Created by Mong Ramos Jr. on 12/7/17 11:47 AM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 12/7/17 5:13 AM
 */

package com.fourvelocity.odoo.http.message;

import com.fourvelocity.odoo.http.client.OdooResponse;

public class SyncResponse{

    public OdooResponse response;

    public OdooResponse getResponse() {
        return response;
    }

    public void setResponse(OdooResponse response) {
        this.response = response;
    }
}
