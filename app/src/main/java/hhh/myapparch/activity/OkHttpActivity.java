package hhh.myapparch.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hhh.myapparch.R;
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
    private final String url="http://blog.csdn.net/tangxl2008008/article/details/51730187";

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
                    public void onResponse(Call call, Response response) throws IOException {
                        MyLog.LogWithString(response.body().string());
                        txt.setText(response.body().string());
                    }
                });
                break;
            case R.id.post:
                break;
        }
    }
}
