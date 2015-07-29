package com.example.sufiy_000.pol.classes;

import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * Created by Thomas on 29/07/2015.
 */
public class AsyncHttpGet extends AsyncTask<String,String, String> {
    @Override
    protected String doInBackground(String... params) {
        try {
            String s = params[0];

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().url(s).build();

            Response response = client.newCall(request).execute();

            String msg = " " + response.body().string();

            return msg;
        } catch (Exception e) {
            Log.e("Welcome", "" + e.toString());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}