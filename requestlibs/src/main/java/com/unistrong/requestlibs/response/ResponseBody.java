package com.unistrong.requestlibs.response;

import com.unistrong.requestlibs.inter.IResponse;

public abstract class ResponseBody<T> implements IResponse {

    public Class clazz;

    public ResponseBody(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public void onSuccess(String json) {
    }

    @Override
    public void onProgress(int progress) {
    }

    @Override
    public abstract void onFailure(String message);

    public abstract void onSuccess(T json);

    public void onJsonFormateError(String message) {
    }
}
