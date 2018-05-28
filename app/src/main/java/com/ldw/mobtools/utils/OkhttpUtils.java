package com.ldw.mobtools.utils;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpUtils  {
    private static OkHttpClient okHttpClient = new OkHttpClient();

    public static String http_get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = new OkHttpClient().newCall(request).execute();
        return response.body().string();
    }
}
