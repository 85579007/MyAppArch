package hhh.myapparch.http.ok;

import android.os.Handler;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import hhh.myapparch.utils.JsonUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hhh on 2016/9/30.
 */
public abstract class JsonCallback<T> extends MyCallback<T> {

    public JsonCallback(Handler handler) {
        super(handler);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if(response.isSuccessful()){
            String s=response.body().string();
            T o= JsonUtils.fromJson(s,this.mType);
            sendSuccess(o);
        }
    }
}
