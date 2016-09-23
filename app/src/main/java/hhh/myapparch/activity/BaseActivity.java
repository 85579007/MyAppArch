package hhh.myapparch.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import org.xutils.x;

import hhh.myapparch.R;
import hhh.myapparch.helper.ActivityManager;
import hhh.myapparch.http.XUtils;

/**
 * Created by hhh on 2016/6/12.
 */
public class BaseActivity extends AppCompatActivity {
    private boolean isExit;
    private Handler handler=new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().put(getClass().getName(),this);
        x.view().inject(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(getClass().getName().equals(MainActivity.class.getName())){
                if(isExit){
                    ActivityManager.getInstance().closeAll();
                }else{
                    isExit=true;
                    XUtils.show(R.string.exit);
                    handler.postDelayed(r,2000);
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private Runnable r=new Runnable() {
        @Override
        public void run() {
            isExit=false;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().remove(getClass().getName());
    }
}
