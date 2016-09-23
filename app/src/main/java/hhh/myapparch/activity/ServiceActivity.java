package hhh.myapparch.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import hhh.myapparch.R;
import hhh.myapparch.service.MyService;

/**
 * Created by hhh on 2016/9/22.
 */
@ContentView(R.layout.activity_service)
public class ServiceActivity extends BaseActivity {
    @ViewInject(R.id.start_service)
    private Button start;
    @ViewInject(R.id.stop_service)
    private Button stop;

    public static void startActivity(Context context){
        Intent intent=new Intent(context,ServiceActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Event(value={R.id.start_service,R.id.stop_service},type= View.OnClickListener.class)
    private void buttonClick(View v){
        switch (v.getId()){
            case R.id.start_service:
                Intent startIntent=new Intent(this, MyService.class);
                startService(startIntent);
                break;
            case R.id.stop_service:
                Intent stopIntent=new Intent(this,MyService.class);
                stopService(stopIntent);
                break;
        }
    }
}
