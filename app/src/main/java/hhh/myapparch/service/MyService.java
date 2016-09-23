package hhh.myapparch.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import hhh.myapparch.activity.ServiceActivity;
import hhh.myapparch.log.MyLog;

/**
 * Created by hhh on 2016/9/22.
 */
public class MyService extends Service {
    public static final String TAG="MyService";
    private MyBinder myBinder=new MyBinder();
    private ServiceActivity.IDownload iDownload;
    private int progress;

    public void setDownload(ServiceActivity.IDownload iDownload) {
        this.iDownload = iDownload;
    }

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

    public void startDownload(){
            MyLog.LogWithString("startDownload");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(progress<100){
                        if(iDownload!=null){
                            iDownload.setProgress(progress);
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

    public class MyBinder extends Binder{
//        public void startDownload(){
//            MyLog.LogWithString("startDownload");
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//
//                }
//            }).start();
//        }
        public MyService getMyService(){
            return MyService.this;
        }
    }
}
