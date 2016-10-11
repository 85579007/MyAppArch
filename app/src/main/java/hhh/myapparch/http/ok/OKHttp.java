package hhh.myapparch.http.ok;

import java.io.File;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by hhh on 2016/9/30.
 */
public class OKHttp {
    private static OKHttp mInstance;
    private OkHttpClient mOkHttpClient;

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
//        handler=new Handler(Looper.getMainLooper());
    }

    public void get(String url, final BaseCallback callback){
        final Request request=new Request.Builder()
                .url(url)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }

    public void post(String url, Map<String,String> params, final BaseCallback callback){
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
        mOkHttpClient.newCall(request).enqueue(callback);
    }

    public void uploadFile(String url,Map<String,Object> params,final BaseCallback callback){
        MultipartBody.Builder builder=new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);

        for(String key:params.keySet()){
            Object o=params.get(key);
            if(o instanceof File){
                File f=(File)o;
                builder.addFormDataPart(key,f.getName(), RequestBody.create(null,f));
            }else{
                builder.addFormDataPart(key,o.toString());
            }
        }

        final Request request=new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }

    public void downloadFile(String url,BaseCallback callback){
        Request request=new Request.Builder()
                .url(url)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }

}
