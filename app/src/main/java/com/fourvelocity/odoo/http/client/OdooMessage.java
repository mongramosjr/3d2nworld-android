/*
 * Created by Mong Ramos Jr. on 12/7/17 11:47 AM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 12/7/17 5:13 AM
 */

package com.fourvelocity.odoo.http.client;

import com.fourvelocity.odoo.http.message.MessageJson;

import org.json.JSONObject;

class OdooMessage {

    public MessageJson body;

    public MessageJson body(JSONObject contents){
        MessageJson messageJson = new MessageJson();
        messageJson.setContents(contents);
        this.body = messageJson;
        return messageJson;
    }

    public MessageJson body(String contents){
        MessageJson messageJson = new MessageJson();
        messageJson.setContents(contents);
        this.body = messageJson;
        return messageJson;
    }

}
