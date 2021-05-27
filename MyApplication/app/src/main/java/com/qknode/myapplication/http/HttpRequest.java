package com.qknode.myapplication.http;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpRequest {

    private Call mCall;

    /**
     * 构造HttpRequest 用于发起网络请求
     *
     * @param url
     */
    public HttpRequest(String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request
                .Builder()
                .get()
                .url(url)
                .build();
        mCall = okHttpClient.newCall(request);
    }


    public void request(Callback mCallback) {
        if (null != mCall) {
            if (mCall.isExecuted()) {
                mCall.clone().enqueue(mCallback);
            } else {
                mCall.enqueue(mCallback);
            }
        }
    }


}
