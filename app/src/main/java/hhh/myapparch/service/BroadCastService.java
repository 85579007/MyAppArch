package hhh.myapparch.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by hhh on 2016/9/23.
 */
public class BroadCastService extends Service {
    public static final String CACTION="com.hhh.progress";

    private Intent intent=new Intent(CACTION);
    private int progress;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startDownload();
    }

    private void startDownload() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progress<100){
                    intent.putExtra("progress",progress++);
                    sendBroadcast(intent);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
