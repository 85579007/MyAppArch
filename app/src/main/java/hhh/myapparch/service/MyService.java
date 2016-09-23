package hhh.myapparch.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import hhh.myapparch.log.MyLog;

/**
 * Created by hhh on 2016/9/22.
 */
public class MyService extends Service {
    public static final String TAG="MyService";
    private MyBinder myBinder=new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        MyLog.LogWithString("created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MyLog.LogWithString("startcommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyLog.LogWithString("destroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    class MyBinder extends Binder{
        public void startDownload(){
            MyLog.LogWithString("startDownload");
        }
    }
}