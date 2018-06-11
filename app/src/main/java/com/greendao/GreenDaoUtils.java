package com.greendao;

import android.app.Application;

import com.auto_generate.local.DaoMaster;
import com.auto_generate.local.DaoSession;

import org.greenrobot.greendao.AbstractDao;

import java.lang.reflect.Method;

/**
 * greendao的自行封装工具类
 * Notes：更新需要改module的gradle文件中修改版本号：
 * //改为最新的版本号
 * //schemaVersion 2
 */
public class GreenDaoUtils {
    private static final String DB_NAME = "app_local";

    private static DaoSession daoSession;

    /**
     * 该方法供application类onCreate调用
     *
     * @param context
     */
    public static void initGreenDao(Application context) {
        GreenDaoOpenHelper greenDaoOpenHelper = new GreenDaoOpenHelper(context, DB_NAME);
        daoSession = new DaoMaster(greenDaoOpenHelper.getWritableDatabase()).newSession();
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
            Method method = daoSessionClazz.getMethod(methodName, new Class[0]);
            return (T) method.invoke(daoSession, new Object[]{});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
