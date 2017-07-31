package com.zhuxiangqing.qqsdkdemo.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhu on 2017/7/31.
 */

public class QQDemoApplication extends Application {
    public static Context mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        if (null == mInstance) {
            mInstance = getApplicationContext();
        }
    }
}
