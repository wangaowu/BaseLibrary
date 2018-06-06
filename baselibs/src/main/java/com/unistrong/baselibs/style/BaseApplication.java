package com.unistrong.baselibs.style;

import android.app.Application;
import android.graphics.Bitmap;

import com.squareup.picasso.Picasso;
import com.unistrong.baselibs.utils.AppUtils;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initPicasso();
        AppUtils.Debug.initProcess(this);
    }

    private boolean isDebugMode() {
        return AppUtils.Debug.isDebug();
    }

    private void initPicasso() {
        //在此做picasso实例的初始化
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.indicatorsEnabled(isDebugMode());
        builder.loggingEnabled(isDebugMode());
        builder.defaultBitmapConfig(Bitmap.Config.ARGB_4444);
        Picasso.setSingletonInstance(builder.build());
    }
}
