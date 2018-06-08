package com.unistrong.demo;

import com.auto_generate.local.DaoSession;
import com.greendao.GreenDaoUtils;
import com.unistrong.baselibs.style.LogCrashApplication;

public class MyApplication extends LogCrashApplication {



    @Override
    public void onCreate() {
        super.onCreate();
        GreenDaoUtils.initGreenDao(this);
    }
}
