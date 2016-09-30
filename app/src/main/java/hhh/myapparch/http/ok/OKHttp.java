package hhh.myapparch.http.ok;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.Map;

import hhh.myapparch.utils.JsonUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hhh on 2016/9/30.
 */
public class OKHttp {
    private static OKHttp mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler handler;

//    private CommonCallback callback;
//    private Callback myCallback=new Callback() {
//        @Override
//        public void onFailure(Call call, IOException e) {
//            sendFailed(null,e,callback);
//        }
//
//        @Override
//        public void onResponse(Call call, Response response) throws IOException {
//            String string=response.body().string();
//            if(callback.mType==String.class){
//                sendSuccess(string,callback);
//            }else{
//                Object o= JsonUtils.fromJson(string,callback.mType);
//                sendSuccess(o,callback);
//            }
//        }
//    };

//    private MyCallback myCallback=new MyCallback(handler, new CommonCallback() {
//        @Override
//        public void onError(Request request, Exception e) {
//
//        }
//
//        @Override
//        public void onResponse(Object response) {
//
//        }
//    });

    public static OKHttp getOKHttp(){
        if(mInstance==null){
            synchronized (OKHttp.class){
                if(mInstance==null){
                    mInstance=new OKHttp();
                }
            }
        }
        return mInstance;
    }

    private OKHttp(){
        mOkHttpClient=new OkHttpClient();
        handler=new Handler(Looper.getMainLooper());
    }

    public void get(String url, final CommonCallback callback){
        final Request request=new Request.Builder()
                .url(url)
                .build();
        mOkHttpClient.newCall(request).enqueue(new MyCallback(handler,callback));
//        mOkHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                sendFailed(request,e,callback);
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String string=response.body().string();
//                if(callback.mType==String.class){
//                    sendSuccess(string,callback);
//                }else{
//                    Object o= JsonUtils.fromJson(string,callback.mType);
//                    sendSuccess(o,callback);
//                }
//            }
//        });
    }

    public void post(String url, Map<String,String> params, final CommonCallback callback){
        FormBody.Builder builder=new FormBody.Builder();
        if(params!=null&&params.size()>0){
            for(Map.Entry<String,String> entry:params.entrySet()){
                builder.add(entry.getKey(),entry.getValue());
            }
        }
        final Request request=new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        mOkHttpClient.newCall(request).enqueue(new MyCallback(handler,callback));
//        mOkHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                sendFailed(request,e,callback);
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String string=response.body().string();
//                if(callback.mType==String.class){
//                    sendSuccess(string,callback);
//                }else{
//                    Object o= JsonUtils.fromJson(string,callback.mType);
//                    sendSuccess(o,callback);
//                }
//            }
//        });
    }

//    private void sendFailed(final Request request, final IOException e, final CommonCallback callback) {
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                if(callback!=null){
//                    callback.onError(request,e);
//                }
//            }
//        });
//    }
//
//    private void sendSuccess(final Object o, final CommonCallback callback) {
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                if(callback!=null){
//                    callback.onResponse(o);
//                }
//            }
//        });
//    }
}
