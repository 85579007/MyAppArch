package hhh.myapparch.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import hhh.myapparch.activity.ServiceActivity;
import hhh.myapparch.http.XUtils;
import hhh.myapparch.log.MyLog;

/**
 * Created by hhh on 2016/9/22.
 */
public class MyService extends Service {
    public static final String TAG="MyService";
    private MyBinder myBinder=new MyBinder();
    private ServiceActivity.IDownload iDownload;
    private int progress=0;

    public void setDownload(ServiceActivity.IDownload iDownload) {
        this.iDownload = iDownload;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MyLog.LogWithString("created");
        XUtils.show("created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MyLog.LogWithString("startcommand");
        XUtils.show("startcommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyLog.LogWithString("destroy");
        XUtils.show("destroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public void startDownload(){
            MyLog.LogWithString("startDownload");
            progress=0;
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
                        progress++;
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
