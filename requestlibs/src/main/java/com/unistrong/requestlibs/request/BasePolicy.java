package com.unistrong.requestlibs.request;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * 请求策略
 */
public class BasePolicy {

    protected static final int DEF_TIMEOUT = 6 * 1000;
    private static OkHttpClient.Builder builder;

    static {
        builder = new OkHttpClient.Builder();
    }

    protected void setConnectTimeout(int timeout) {
        builder.connectTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.readTimeout(10 * 1000, TimeUnit.MILLISECONDS);
        builder.writeTimeout(10 * 1000, TimeUnit.MILLISECONDS);
    }

    protected OkHttpClient newClient() {
        return builder.build();
    }

}
