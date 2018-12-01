package com.hackapi.childsafe.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.hackapi.childsafe.MainActivity;
import com.hackapi.childsafe.adapters.FlaggedItemsAdapter;
import com.hackapi.childsafe.pojos.FlaggedData;
import com.onesignal.OSNotification;
import com.onesignal.OneSignal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CustomNotificationHandler implements OneSignal.NotificationReceivedHandler {
    Context ctx;
    String url = "http://web.child-safe.tech/api/child_log/blocked";
    private String LOG_TAG = "API Call";
    FlaggedItemsAdapter adapter;
    private String REQUEST_TAG = "API Request";

    public CustomNotificationHandler(Context ctx, FlaggedItemsAdapter flaggedItemsAdapter) {
        this.ctx = ctx;
        adapter = flaggedItemsAdapter;
    }

    @Override
    public void notificationReceived(OSNotification notification) {
        JSONObject data = notification.payload.additionalData;
        String customKey;

        if (data != null) {
            customKey = data.optString("customkey", null);
            if (customKey != null)
                Log.i("OneSignalExample", "customkey set with value: " + customKey);
        }

        Log.d("incoming_message", data != null? data.toString():"NULL");


        //query API
        makeCall();
    }

    private void makeCall() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    ArrayList<FlaggedData> allFlaggedData = new ArrayList<>();
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(LOG_TAG, response.toString());
                        if (response != null) {
                            JSONObject obj = new JSONObject();
                            try{
                                allFlaggedData.clear();
                                for(int i = 0; i<response.length(); i++){
                                    obj = response.getJSONObject(i);
                                    String imgUrl = obj.getString("url");
                                    String dateTime = obj.getString("created_at");
                                    String initiatorUrl = obj.getString("initiator");
                                    allFlaggedData.add(new FlaggedData(imgUrl, initiatorUrl, dateTime));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }finally {
                                PreferenceData.setPrefFlaggedSites(ctx,allFlaggedData);
                                adapter.setAllFlaggedData(allFlaggedData);
                                adapter.notifyDataSetChanged();
                            }

                        } else {

                            Toast.makeText(ctx, "Sorry, there is a problem with your network.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(LOG_TAG, "onErrorResponse: Error listener fired: " + error.getMessage());
                if (error.toString().contains("NoConnectionError")) {
                    Log.d(LOG_TAG, "onErrorResponse: "+ error);

                }
                VolleyLog.d(LOG_TAG, "Error: " + error.getMessage());

            }
        });
        // Adding JsonObject request to request queue
        AppSingleton.getInstance(ctx).addToRequestQueue(jsonArrayRequest, REQUEST_TAG);
    }
}
