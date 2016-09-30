package hhh.myapparch.application;

import android.app.Application;

import hhh.myapparch.BuildConfig;
import hhh.myapparch.dialog.Loading;
import hhh.myapparch.http.x.XUtils;
import hhh.myapparch.log.MyLog;

/**
 * Created by hhh on 2016/6/12.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        XUtils.init(this);
        Loading.init(this);
        MyLog.initLog(BuildConfig.LOG_DEBUG);
    }
}
