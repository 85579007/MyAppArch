package hhh.myapparch.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hhh.myapparch.R;
import hhh.myapparch.http.XUtils;
import hhh.myapparch.log.MyLog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
    private final String url="http://119.29.193.241/subject/getsubjects";

    public static void startAcitvity(Context context){
        Intent intent=new Intent(context,OkHttpActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.get, R.id.post})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get:
                OkHttpClient okHttpClient=new OkHttpClient();
                Request request=new Request.Builder()
                        .url(url)
                        .build();
                Call call=okHttpClient.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        MyLog.LogWithString("failure");
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        final String s=response.body().string();
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
                break;
        }
    }
}
