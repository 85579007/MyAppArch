package hhh.myapparch.http;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import hhh.myapparch.dialog.Loading;
import hhh.myapparch.log.MyLog;

/**
 * Created by hhh on 2016/6/13.
 */
public class XUtils {
    private static Context mContext;
    private static Callback.Cancelable cancelable;

    public static void init(Application application){
        mContext=application.getApplicationContext();
        x.Ext.init(application);
        MyLog.LogWithString("xutils init done");
    }

    public static <T> void send(HttpMethod method,RequestParams params, Callback.CommonCallback<T> callback, boolean loading){
        if(loading){
            Loading.show();
        }
        if(params!=null){
            cancelable=x.http().request(method,params,callback);
        }
    }

    public static void cancel(){
        cancelable.cancel();
    }

    public static void show(String text){
        Toast.makeText(mContext,text,Toast.LENGTH_SHORT).show();;
    }

    public static void show(int resId){
        Toast.makeText(mContext,resId,Toast.LENGTH_SHORT).show();
    }
}
