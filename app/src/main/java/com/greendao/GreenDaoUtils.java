package com.greendao;

import android.app.Application;

import com.auto_generate.local.DaoMaster;
import com.auto_generate.local.DaoSession;

import org.greenrobot.greendao.AbstractDao;

import java.lang.reflect.Method;

public class GreenDaoUtils {
    private static final String DB_NAME = "app_local";

    private static DaoSession daoSession;

    /**
     * 该方法供application类onCreate调用
     *
     * @param context
     */
    public static void initGreenDao(Application context) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        daoSession = new DaoMaster(devOpenHelper.getWritableDatabase()).newSession();
    }

    /**
     * 构建实体Dao类
     *
     * @param beanClazz Person.class
     * @param <T>       Person+Dao
     * @return
     */
    public static <T extends AbstractDao> T newXXdao(Class beanClazz) {
        try {
            String methodName = "get" + beanClazz.getSimpleName() + "Dao";
            Class<? extends DaoSession> daoSessionClazz = daoSession.getClass();
            Method method = daoSessionClazz.getMethod(methodName, null);
            return (T) method.invoke(daoSession, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
