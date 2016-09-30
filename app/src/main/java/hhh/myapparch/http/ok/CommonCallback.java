package hhh.myapparch.http.ok;

import android.os.Handler;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hhh on 2016/9/30.
 */
public abstract class CommonCallback<T> {
    Type mType;
    public CommonCallback(){
        Type superClass=this.getClass().getGenericSuperclass();
        mType =((ParameterizedType)superClass).getActualTypeArguments()[0];
    }

    public abstract void onError(Request request, Exception e);

    public abstract void onResponse(T response);
}
