/*
 * Created by Mong Ramos Jr. on 12/17/17 7:45 AM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 12/17/17 7:40 AM
 */

package com.basement720.odoo.http.client;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.basement720.odoo.http.message.MessageJson;
import com.basement720.odoo.http.message.RequestInterface;
import com.basement720.odoo.log.OdooLog;

import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;

public class OdooRequest extends OdooMessage implements RequestInterface {

    private URI uri;

    private String method;

    private String protocolVersion;

    public String urlPath;

    public OdooRequest(String url, @Nullable String method, @Nullable JSONObject data){
        if(method != null){
            withMethod(method);
        }
        this.uri = createUri(url);

        this.body = body(data);
    }

    public URI createUri(URI uri_reference)
    {
        withUri(uri_reference);
        urlPath = uri_reference.getPath();
        return uri_reference;
    }

    public URI createUri(@NonNull String uri_reference){
        URI uri_ref = null;
        try {
            uri_ref = new URI(uri_reference);
        } catch (URISyntaxException e) {
            OdooLog.e(e.getMessage());
        }
        withUri(uri_ref);
        urlPath = uri_ref.getPath();
        return uri_ref;

    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public void withMethod(String method) {
        this.method = method;
    }

    @Override
    public URI getUri() {
        return uri;
    }

    @Override
    public void withUri(URI uri) {
        this.uri = uri;
    }

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
}


