package com.cloudream.hime.business.base;

import android.app.Application;

/**
 * Created by admin on 2016/8/24.
 */
public class BaseApplication extends Application{
    private static BaseApplication INSTANCE = null;
    public static BaseApplication getInstance(){
        if(INSTANCE == null){
            INSTANCE = new BaseApplication();
        }
        return  INSTANCE;
    }

}
