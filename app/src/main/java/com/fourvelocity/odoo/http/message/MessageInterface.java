/*
 * Created by Mong Ramos Jr. on 12/7/17 11:47 AM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 10/8/17 7:43 PM
 */

package com.fourvelocity.odoo.http.message;

/**
 * Created by mong on 10/8/17.
 */

public interface MessageInterface {

    MessageJson getBody();

    String getProtocolVersion();

    void withBody(MessageJson body);

    void withProtocolVersion(String protocolVersion);


}
