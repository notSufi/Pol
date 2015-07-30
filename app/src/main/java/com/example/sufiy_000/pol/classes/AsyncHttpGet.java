package com.example.sufiy_000.pol.classes;

import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Thomas on 29/07/2015.
 */
public class AsyncHttpGet extends AsyncTask<String,Long, String> {
    @Override
    protected String doInBackground(String... params) {
        try {
            String s = params[0];

            Log.d("Http", s);

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().url(s).get().build();

            Response response = client.newCall(request).execute();

            return response.body().string();
        } catch (Exception e) {
            Log.e("Welcome", "" + e.toString());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
    }
}