/*
 * Created by Mong Ramos Jr. on 12/17/17 7:45 AM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 12/17/17 7:40 AM
 */

package com.basement720.odoo.http.client;

import com.basement720.odoo.http.message.MessageJson;

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
