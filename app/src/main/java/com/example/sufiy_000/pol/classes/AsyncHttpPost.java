package com.example.sufiy_000.pol.classes;

import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

import okio.BufferedSink;

/**
 * Created by Thomas on 29/07/2015.
 */
public class AsyncHttpPost extends AsyncTask<String,String, Response> {
    @Override
    protected Response doInBackground(String... params) {
        try {
            String url = params[0];

            OkHttpClient client = new OkHttpClient();

            MediaType MEDIA_TYPE = MediaType.parse(params[1]);

            Request request = new Request.Builder().post(RequestBody.create(MEDIA_TYPE, url)).build();

            Response response = client.newCall(request).execute();

            return response;
        } catch (Exception e) {
            Log.e("Welcome", "" + e.toString());
            return null;
        }
    }

    @Override
    protected void onPostExecute(Response response) {
        super.onPostExecute(response);
    }
}