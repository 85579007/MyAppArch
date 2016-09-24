package hhh.myapparch.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hhh.myapparch.R;
import hhh.myapparch.log.MyLog;

/**
 * Created by Administrator on 2016/9/25 0025.
 */
public class ButterKnifeActivity extends BaseActivity {
    @BindView(R.id.btn1)
    Button btn1;

    public static void startAcitvity(Context context){
        Intent intent=new Intent(context,ButterKnifeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butterknife);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn1) void btnClick(){
        MyLog.LogWithString("btn1 click");
        Toast.makeText(this,"btn1 click",Toast.LENGTH_LONG).show();
    }
}
