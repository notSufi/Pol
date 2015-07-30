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

            if (response.code() == 200) {
                InputStream is = null;
                try {
                    is = response.body().byteStream();
                    byte[] buff = new byte[1024 * 4];
                    long downloaded = 0;
                    long target = response.body().contentLength();

                    publishProgress(0L, target);
                    while (true) {
                        int read = is.read(buff);
                        if (read == -1) {
                            break;
                        }

                        downloaded += read;
                        publishProgress(downloaded, target);
                        if (isCancelled()) {
                            return null;
                        }
                    }
                } catch (IOException ignore) {
                    return null;
                } finally {
                    if (is != null) {
                        is.close();
                    }
                }
            }
            else {
                return null;
            }

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