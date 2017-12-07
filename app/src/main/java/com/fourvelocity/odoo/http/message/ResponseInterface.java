/*
 * Created by Mong Ramos Jr. on 12/7/17 11:47 AM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 10/8/17 11:54 AM
 */

package com.fourvelocity.odoo.http.message;

import android.support.annotation.Nullable;

/**
 * Created by mong on 10/8/17.
 */

public interface ResponseInterface extends MessageInterface {

    int getStatusCode();

    String getReasonPhrase();

    void withStatus(int code, @Nullable String reasonPhrase);


}
