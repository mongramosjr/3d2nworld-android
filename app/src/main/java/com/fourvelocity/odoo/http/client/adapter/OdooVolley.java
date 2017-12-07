/*
 * Created by Mong Ramos Jr. on 12/7/17 11:47 AM
 *
 * Copyright (c) 2017 Brainbox Inc. All rights reserved.
 *
 * Last modified 12/7/17 5:13 AM
 */

package com.fourvelocity.odoo.http.client.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.fourvelocity.odoo.http.client.OdooAdapter;
import com.fourvelocity.odoo.http.client.OdooRequest;
import com.fourvelocity.odoo.http.client.OdooResponse;
import com.fourvelocity.odoo.http.message.SyncResponse;
import com.fourvelocity.odoo.log.FoVeLog;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

public class OdooVolley<T> extends OdooAdapter implements Response.Listener<JSONObject>, Response.ErrorListener {

    public static final String TAG = OdooVolley.class.getName();

    public static Integer REQUEST_TIMEOUT_MS = DefaultRetryPolicy.DEFAULT_TIMEOUT_MS;
    public static Integer DEFAULT_MAX_RETRIES = DefaultRetryPolicy.DEFAULT_MAX_RETRIES;

    private RequestQueue requestQueue;
    protected String serverUrl;

    protected Gson gson;

    public OdooVolley(Context context, @Nullable OdooRequest request) {
        gson = new Gson();

        if(request != null){
            URI uri = request.getUri();
            URI serverUri = null;
            try {
                serverUri = new URI(uri.getScheme(), null, uri.getHost(), uri.getPort(), null, null, null);
            } catch (URISyntaxException e) {
                FoVeLog.e(e.getMessage());
            }
            if(serverUri != null) {
                try {
                    serverUrl = serverUri.toURL().toString();
                } catch (MalformedURLException e) {
                    FoVeLog.e(e.getMessage());
                }
            }
        }

//        //responseQueue = new OdooResponseQueue();
//        requestQueue = Volley.newRequestQueue(context,
//                new HttpClientStack(OdooSafeClient.getSafeClient(true)));
        requestQueue = Volley.newRequestQueue(context);
    }

    public SyncResponse send(OdooRequest request, SyncResponse syncResponse ) {
        return _send(request, syncResponse);
    }

    private SyncResponse _send(OdooRequest request, SyncResponse syncResponse ){
        String url = null;
        try {
            url = request.getUri().toURL().toString();
        } catch (MalformedURLException e) {
            FoVeLog.e(e.getMessage());
        }

        if(url == null) return null;

        JSONObject postData = request.getBody().getContents();

        if(syncResponse == null) {

            JsonObjectRequest jsonRequest = new JsonObjectRequest(url, postData, OdooVolley.this, OdooVolley.this);
            jsonRequest.setRetryPolicy(new DefaultRetryPolicy(REQUEST_TIMEOUT_MS, DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(jsonRequest);

            return null;
        }else {

            RequestFuture<JSONObject> requestFuture = RequestFuture.newFuture();
            JsonObjectRequest jsonRequest = new JsonObjectRequest(url, postData, requestFuture, requestFuture);
            jsonRequest.setRetryPolicy(new DefaultRetryPolicy(REQUEST_TIMEOUT_MS, DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            requestQueue.add(jsonRequest);

            OdooResponse odooResponse = new OdooResponse();
            try {
                try {
                    odooResponse.body(requestFuture.get());
                } catch (InterruptedException e) {
                    odooResponse.withStatus(400,e.getMessage());
                    FoVeLog.e(e.getMessage());
                }
                syncResponse.setResponse(odooResponse);
            } catch (ExecutionException e) {
                odooResponse.withStatus(400,e.getMessage());
                syncResponse.setResponse(odooResponse);
                FoVeLog.e(e.getMessage());
            }

            return syncResponse;
        }
    }

//    private createResponses

    @Override
    public void onErrorResponse(VolleyError error) {

        FoVeLog.d("ERROR RESPONSE : " + error.getMessage());
//        String message = error.getMessage();
//        int responseCode = -1;
//        if (error.networkResponse != null) {
//            message = "Server Error :" + error.networkResponse.statusCode;
//            switch (error.networkResponse.statusCode) {
//                case 400:
//                    responseCode = Odoo.ErrorCode.OdooServerError.get();
//                    break;
//                case 404:
//                    responseCode = Odoo.ErrorCode.InvalidURL.get();
//                    break;
//                default:
//                    responseCode = Odoo.ErrorCode.UnknownError.get();
//            }
//        }
//        OdooError odooError = new OdooError(message, error);
//        odooError.setResponseCode(responseCode);
//        if (error instanceof TimeoutError) {
//            odooError.setMessage("Request Time out");
//            odooError.setServerTrace("Requested too many records. \n\n" +
//                    "You can update values before requesting data:\n" +
//                    "Odoo.REQUEST_TIMEOUT_MS\n" +
//                    "Odoo.DEFAULT_MAX_RETRIES");
//        }
//        try {
//            IOdooResponse response = responseQueue.get(postData.getInt("id"));
//            if (response != null) {
//                response.onError(odooError);
//                responseQueue.remove(postData.getInt("id"));
//            }
//        } catch (JSONException e) {
//            FoVeLog.e(e.getMessage(), e);
//        }

    }

    @Override
    public void onResponse(JSONObject response) {
        FoVeLog.d("RESPONSE:" + response);

//        OdooResponse responseMap = parseToResponse(response);
//        if (responseMap != null) {
//            int id = responseMap.id;
//            IOdooResponse odooResponse = responseQueue.get(id);
//            if (odooResponse != null) {
//                if (responseMap.error != null) {
//                    OdooError error = OdooError.parse(responseMap.error);
//                    error.setResponseCode(Odoo.ErrorCode.OdooServerError.get());
//                    odooResponse.onError(error);
//                } else {
//                    odooResponse.onResponse(responseMap.result);
//                }
//                responseQueue.remove(id);
//            }
//        }
    }

    private OdooResponse parseToResponse(JSONObject response) {
        try {
            // Fixed for direct array in result
            // It will add one more key to result:(result:[] become result:{"result": []});
            if (response.has("result")) {
                if (!(response.get("result") instanceof JSONObject)) {
                    JSONObject obj = new JSONObject();
                    obj.put("result", response.get("result"));
                    response.put("response", obj);
                }
            } else if (!response.has("error")) {
                JSONObject result = response;
                response = new JSONObject();
                response.put("response", result);
            }
        } catch (Exception e) {
            FoVeLog.e(e.getMessage(), e);
        }
        return gson.fromJson(response.toString(), OdooResponse.class);
    }

    @Override
    public SyncResponse SessionInfo(OdooRequest request) {

        ///web/session/get_session_info
        SyncResponse syncResponse = new SyncResponse();
        return _send(request, syncResponse);
    }

    @Override
    public SyncResponse databaseList(OdooRequest request) {
        ///web/database/list
        SyncResponse syncResponse = new SyncResponse();
        return _send(request, syncResponse);
    }

    @Override
    public SyncResponse authenticate(OdooRequest request) {
        ///web/session/authenticate
        SyncResponse syncResponse = new SyncResponse();
        return _send(request, syncResponse);
    }

    @Override
    public SyncResponse odooVersion(OdooRequest request) {
        ///web/webclient/version_info
        return _send(request, null);
    }

    @Override
    public SyncResponse searchRead(OdooRequest request) {
        ///web/dataset/search_read
        SyncResponse syncResponse = new SyncResponse();
        return send(request, syncResponse);
    }

    @Override
    public SyncResponse executeWorkFlow(OdooRequest request) {

        ///web/dataset/exec_workflow
        SyncResponse syncResponse = new SyncResponse();
        return _send(request, syncResponse);
    }

    @Override
    public SyncResponse read(OdooRequest request, String model) {
        ///web/dataset/call_kw/" + model + "/read
        SyncResponse syncResponse = new SyncResponse();
        return _send(request, syncResponse);
    }

    @Override
    public SyncResponse callMethod(OdooRequest request) {
        ///web/dataset/call_kw
        SyncResponse syncResponse = new SyncResponse();
        return _send(request, syncResponse);
    }
}
