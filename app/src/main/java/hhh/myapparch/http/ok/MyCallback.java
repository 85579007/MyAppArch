package hhh.myapparch.http.ok;

import android.os.Handler;

import java.io.IOException;

import hhh.myapparch.utils.JsonUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hhh on 2016/9/30.
 */
public class MyCallback implements Callback {
    CommonCallback callback;
    Handler handler;

    public MyCallback(Handler handler,CommonCallback resultCallback) {
        this.handler=handler;
        this.callback=resultCallback;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        sendFailed(null,e,callback);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        String string=response.body().string();
        if(callback.mType==String.class){
            sendSuccess(string,callback);
        }else{
            Object o= JsonUtils.fromJson(string,callback.mType);
            sendSuccess(o,callback);
        }
    }

    private void sendFailed(final Request request, final IOException e, final CommonCallback callback) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(callback!=null){
                    callback.onError(request,e);
                }
            }
        });
    }

    private void sendSuccess(final Object o, final CommonCallback callback) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(callback!=null){
                    callback.onResponse(o);
                }
            }
        });
    }
}
