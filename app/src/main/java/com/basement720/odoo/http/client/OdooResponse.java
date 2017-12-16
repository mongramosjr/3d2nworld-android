/*
 * Created by Mong Ramos Jr. on 12/17/17 7:45 AM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 12/17/17 7:40 AM
 */

package com.basement720.odoo.http.client;

import android.support.annotation.Nullable;

import com.basement720.odoo.http.message.MessageJson;
import com.basement720.odoo.http.message.ResponseInterface;

public class OdooResponse extends OdooMessage implements ResponseInterface {

    private String protocolVersion;

    private int statusCode;
    private String reasonPhrase;

    @Override
    public MessageJson getBody() {
        return body;
    }

    @Override
    public String getProtocolVersion() {
        return protocolVersion;
    }

    @Override
    public void withBody(MessageJson body) {
        this.body = body;
    }

    @Override
    public void withProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getReasonPhrase() {
        return reasonPhrase;
    }

    @Override
    public void withStatus(int code, @Nullable String reasonPhrase) {
        this.statusCode = code;
        if(reasonPhrase!=null) {
            if (!reasonPhrase.isEmpty()) {
                this.reasonPhrase = reasonPhrase;
            }
        }
    }
}
