package hhh.myapparch.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hhh.myapparch.R;
import hhh.myapparch.log.MyLog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/25 0025.
 */
public class OkHttpActivity extends BaseActivity {
    @BindView(R.id.get)
    Button get;
    @BindView(R.id.post)
    Button post;
    @BindView(R.id.txt)
    TextView txt;
    private final String url = "http://119.29.193.241/subject/getsubjects";
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.getimg)
    Button getimg;

    private OkHttpClient okHttpClient;

    public static void startAcitvity(Context context) {
        Intent intent = new Intent(context, OkHttpActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        ButterKnife.bind(this);

        okHttpClient = new OkHttpClient();
    }

    @OnClick({R.id.get, R.id.post,R.id.getimg})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get:
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Call call = okHttpClient.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        MyLog.LogWithString("failure");
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        final String s = response.body().string();
                        MyLog.LogWithString("ok");
//                        txt.setText(response.body().string());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                txt.setText(s);
                            }
                        });
                    }
                });
//                RequestParams params=new RequestParams(url);
//                x.http().get(params, new org.xutils.common.Callback.CommonCallback<String>() {
//                    @Override
//                    public void onSuccess(String result) {
//                        //XUtils.show(result);
//                        JSONObject obj= JSON.parseObject(result);
//                        XUtils.show("message"+obj.getString("message"));
//
//                        JSONArray arr=obj.getJSONArray("data");
//                        JSONObject jo1= (JSONObject) arr.get(0);
//                        txt.setText("array ="+arr.size()+",1=>{id="+jo1.getString("id")+"}");
//                    }
//
//                    @Override
//                    public void onError(Throwable ex, boolean isOnCallback) {
//                        XUtils.show("net error");
//                    }
//
//                    @Override
//                    public void onCancelled(CancelledException cex) {
//
//                    }
//
//                    @Override
//                    public void onFinished() {
//
//                    }
//                });
                break;
            case R.id.post:
                String url1 = "http://119.29.193.241/user/login";
                RequestBody requestBody = new FormBody.Builder()
                        .add("phone", "13500001111")
                        .add("pwd", "12345678")
                        .build();
                Request request1 = new Request.Builder()
                        .url(url1)
                        .post(requestBody)
                        .build();
                okHttpClient.newCall(request1).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        //XUtils.show("net error");
                        MyLog.LogWithString("net error");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String txt = response.body().string();
                        JSONObject obj = JSON.parseObject(txt);
                        int code = obj.getInteger("code");
                        if (code == 0) {
                            JSONObject data = JSON.parseObject(obj.getString("data"));
                            //MyLog.LogWithString("return email="+data.getString("email"));
                            showInUI("return email=" + data.getString("email"));
                        } else {
                            //MyLog.LogWithString(obj.getString("message"));
                            showInUI(obj.getString("message"));
                        }
                    }
                });
                break;
            case R.id.getimg:
                String imgurl="http://img1.gtimg.com/ninja/1/2016/09/ninja147463577893061.jpg";
                Observable.just(imgurl)
                        .map(new Func1<String, Bitmap>() {
                            @Override
                            public Bitmap call(String s) {
                                Request request2=new Request.Builder()
                                        .url(s)
                                        .build();
                                try {
                                    Response response=okHttpClient.newCall(request2).execute();
                                    return BitmapFactory.decodeStream(response.body().byteStream());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return null;
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Bitmap>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                MyLog.LogWithString("error");
                            }

                            @Override
                            public void onNext(Bitmap bitmap) {
                                image.setImageBitmap(bitmap);
                            }
                        });
                break;
        }
    }

    private void showInUI(String s) {
        final String fs = s;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txt.setText(fs);
            }
        });
    }
}
