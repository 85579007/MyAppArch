package hhh.myapparch.http.ok;

import android.os.Handler;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import hhh.myapparch.log.MyLog;
import hhh.myapparch.utils.JsonUtils;
import hhh.myapparch.utils.L;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hhh on 2016/9/30.
 */
public abstract class BaseCallback<T> implements Callback {
    Type mType;


    public BaseCallback(Handler handler) {
        this.handler=handler;
//        Type superClass=this.getClass().getGenericSuperclass();
//        mType =((ParameterizedType)superClass).getActualTypeArguments()[0];
    }

//    public BaseCallback(Handler handler,Type type){
//        this.handler=handler;
//        this.mType=type;
//    }

    public abstract void onError(String error);
    public abstract void onSuccess(T response);

    Handler handler;

//    public BaseCallback(Handler handler) {
//        this.handler=handler;
//    }

    @Override
    public void onFailure(Call call, IOException e) {
        sendFailed(e.toString());
    }

//    public void onResponse(Call call, Response response) throws IOException {
//        String string=response.body().string();
//        MyLog.LogWithString(string);
//        if(this.mType==String.class){
//            sendSuccess((T)string);
//        }else{
//            T o= JsonUtils.fromJson(string,this.mType);
//            sendSuccess(o);
//        }
//    }

    protected void sendFailed(final String error) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                onError(error);
            }
        });
    }

    protected void sendSuccess(final T o) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                 onSuccess(o);
            }
        });
    }
}
