package hhh.myapparch.http.ok;

import android.os.Handler;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hhh on 2016/10/8.
 */
public abstract class StringCallback extends MyCallback<String> {

    public StringCallback(Handler handler) {
        super(handler);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if(response.isSuccessful()){
            String s=response.body().string();
            sendSuccess(s);
        }
    }
}
