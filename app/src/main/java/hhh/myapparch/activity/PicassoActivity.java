package hhh.myapparch.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hhh.myapparch.R;

/**
 * Created by hhh on 2016/9/26.
 */
public class PicassoActivity extends BaseActivity {
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.img1)
    ImageView img1;

    public static void startActivity(Context context){
        Intent intent=new Intent(context,PicassoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_picasso);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn1)
    public void onClick() {
        String imgurl="http://img1.gtimg.com/ninja/1/2016/09/ninja147463577893061.jpg";
        Picasso.with(PicassoActivity.this)
                .load(imgurl)
                .into(img1);
    }
}
