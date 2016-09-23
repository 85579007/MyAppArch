package hhh.myapparch.log;

import android.view.View;

import com.socks.library.KLog;

/**
 * Created by hhh on 2016/6/12.
 */
public class MyLog {

    public static void initLog(boolean islog){
        KLog.init(islog);
    }

    public static void LogWithString(String msg){
        KLog.d(msg);
    }

    public static void LogWithStringTag(String tag,String msg){
        KLog.d(tag,msg);
    }

    public static void LogWithJson(String json){
        KLog.json(json);
    }

    public static void LogWithJsonTag(String tag,String json){
        KLog.json(tag,json);
    }
}
