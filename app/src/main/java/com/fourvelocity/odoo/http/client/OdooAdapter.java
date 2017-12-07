/*
 * Created by Mong Ramos Jr. on 12/7/17 11:47 AM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 12/7/17 5:13 AM
 */

package com.fourvelocity.odoo.http.client;

import com.fourvelocity.odoo.http.message.SyncResponse;

/**
 * Created by mong on 10/8/17.
 */

public abstract class OdooAdapter {

    public abstract SyncResponse SessionInfo(OdooRequest request);

    public abstract SyncResponse databaseList(OdooRequest request);

    public abstract SyncResponse authenticate(OdooRequest request);

    public abstract SyncResponse odooVersion(OdooRequest request);

    public abstract SyncResponse searchRead(OdooRequest request);

    public abstract SyncResponse executeWorkFlow(OdooRequest request);

    public abstract SyncResponse read(OdooRequest request, String model);

    public abstract SyncResponse callMethod(OdooRequest request);
}


/**

sessionInfo
    /web/session/get_session_info
getDatabaseList
    /web/database/list
authenticate
    /web/session/authenticate
odooVersionInfo
    /web/webclient/version_info


    ------------

searchRead
    /web/dataset/search_read

executeWorkFlow
    /web/dataset/exec_workflow

read
    /web/dataset/call_kw/" + model + "/read

callMethod
    /web/dataset/call_kw

    */
