/*
 * Created by Mong Ramos Jr. on 12/17/17 7:45 AM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 12/17/17 7:40 AM
 */

package com.basement720.odoo.log;

import android.os.Debug;
import android.util.Log;

/**
 * Created by mong on 10/6/17.
 */

public class OdooLog {

    public static final String TAG = OdooLog.class.getSimpleName();

    public static void v(String mesg) {
        if (Debug.isDebuggerConnected() || Debug.waitingForDebugger()) {
            Log.v(TAG, mesg);
        }
    }

    public static void d(String mesg) {
        if (Debug.isDebuggerConnected() || Debug.waitingForDebugger()) {
            Log.d(TAG, mesg);
        }
    }

    public static void e(String mesg) {
        if (Debug.isDebuggerConnected() || Debug.waitingForDebugger()) {
            Log.e(TAG, mesg);
        }
    }

    public static void e(String mesg, Throwable tr) {
        if (Debug.isDebuggerConnected() || Debug.waitingForDebugger()) {
            Log.e(TAG, mesg, tr);
        }
    }

}
