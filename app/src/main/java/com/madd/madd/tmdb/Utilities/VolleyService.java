package com.madd.madd.tmdb.Utilities;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


public class VolleyService {

    private RequestQueue queue;


    private static VolleyService volleyService;

    private VolleyService(Context context){
        this.queue = Volley.newRequestQueue(context);
    }

    public static VolleyService getInstance(Context context){
        if( volleyService == null ){
            volleyService = new VolleyService(context);
        }
        return volleyService;
    }




    public void getData( String url, boolean useCache ,GetVolleyResponse resultCallback) {
        try {

            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.GET,
                    url, null,
                    resultCallback::notifySuccess,
                    error -> {
                        if (error.networkResponse != null && error.networkResponse.data != null) {
                            try {
                                String body = new String(error.networkResponse.data, "UTF-8");
                                JSONObject response = new JSONObject(body);
                                String message = response.getString("status_message");
                                if (message.contains("could not be found")) {
                                    resultCallback.notifyError(References.NOT_FOUND_ITEM_ERROR);
                                } else {
                                    resultCallback.notifyError(References.SERVER_ERROR);
                                }
                            } catch (UnsupportedEncodingException | JSONException e) {
                                e.printStackTrace();
                                resultCallback.notifyError(References.SERVER_ERROR);
                            }
                        } else {
                            resultCallback.notifyError(References.NOT_INTERNET_ERROR);
                        }
                    }
            );
            request.setRetryPolicy(new DefaultRetryPolicy(
                    DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                    0,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            request.setShouldCache(useCache);
            queue.add(request);

        } catch(Exception ignored) {
            resultCallback.notifyError(References.UNRECOGNIZED_ERROR);

        }
    }




    public interface GetVolleyResponse {
        void notifySuccess(JSONObject response);
        void notifyError(String error);
    }


}