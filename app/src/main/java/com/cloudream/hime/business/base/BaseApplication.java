package com.cloudream.hime.business.base;

import android.app.Application;

import com.cloudream.hime.business.common.database.CacheDataBase;

/**
 * Created by admin on 2016/8/24.
 */
public class BaseApplication extends Application{
    private static BaseApplication INSTANCE = null;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        CacheDataBase.getInstance(getApplicationContext());
    }
    public static BaseApplication getInstance(){
        return  INSTANCE;
    }
}
