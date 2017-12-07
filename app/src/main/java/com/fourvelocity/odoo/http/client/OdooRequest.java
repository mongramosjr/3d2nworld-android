/*
 * Created by Mong Ramos Jr. on 12/7/17 11:47 AM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 12/7/17 5:13 AM
 */

package com.fourvelocity.odoo.http.client;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.fourvelocity.odoo.http.message.MessageJson;
import com.fourvelocity.odoo.http.message.RequestInterface;
import com.fourvelocity.odoo.log.FoVeLog;

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
            FoVeLog.e(e.getMessage());
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


