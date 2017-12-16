/*
 * Created by Mong Ramos Jr. on 12/17/17 7:45 AM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 12/17/17 7:40 AM
 */

package com.basement720.odoo.http.message;

import java.net.URI;

public interface RequestInterface extends MessageInterface {

    String getMethod();

    void withMethod(String method);

    URI getUri();

    void withUri(URI uri);
}
