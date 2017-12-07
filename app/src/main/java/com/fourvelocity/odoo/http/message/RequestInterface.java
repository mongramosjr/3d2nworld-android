/*
 * Created by Mong Ramos Jr. on 12/7/17 11:47 AM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 10/8/17 9:08 PM
 */

package com.fourvelocity.odoo.http.message;

import android.net.Uri;

import java.net.URI;

public interface RequestInterface extends MessageInterface {

    String getMethod();

    void withMethod(String method);

    URI getUri();

    void withUri(URI uri);
}
