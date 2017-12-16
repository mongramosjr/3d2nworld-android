/*
 * Created by Mong Ramos Jr. on 12/17/17 7:45 AM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 12/17/17 7:40 AM
 */

package com.basement720.odoo.http.message;

import android.support.annotation.Nullable;

/**
 * Created by mong on 10/8/17.
 */

public interface ResponseInterface extends MessageInterface {

    int getStatusCode();

    String getReasonPhrase();

    void withStatus(int code, @Nullable String reasonPhrase);


}
