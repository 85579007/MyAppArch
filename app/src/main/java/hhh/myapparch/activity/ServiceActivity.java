package hhh.myapparch.activity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import hhh.myapparch.R;
import hhh.myapparch.service.BroadCastService;
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

    public interface IDownload{
        public void setProgress(int i);
    }

    private ProgressDialog dialog;
    private BroadcastReceiver receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(BroadCastService.CACTION)){
                int progress=intent.getIntExtra("progress",0);
                dialog.setProgress(progress);
            }
        }
    };

    public static void startActivity(Context context){
        Intent intent=new Intent(context,ServiceActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        IntentFilter filter=new IntentFilter();
        filter.addAction(BroadCastService.CACTION);
        registerReceiver(receiver,filter);
    }

    @Event(value={R.id.start_service,R.id.stop_service,R.id.bind_service,R.id.unbind_service,R.id.download,R.id.broadcast},type= View.OnClickListener.class)
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
            case R.id.bind_service:
                Intent bindintent=new Intent(this,MyService.class);
                bindService(bindintent,conn,BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(conn);
                break;
            case R.id.download:
                createdialog();
                myService.startDownload();

                break;
            case R.id.broadcast:
                createdialog();
                Intent intent=new Intent(this,BroadCastService.class);
                startService(intent);

                break;
        }
    }

    private void createdialog() {
        if(dialog==null) {
            dialog = new ProgressDialog(this);
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setTitle("进度对话框");
            dialog.setMessage("进度条");
            dialog.setIcon(android.R.drawable.ic_dialog_map);
            dialog.setIndeterminate(false);
            dialog.setMax(100);
            dialog.setCancelable(true);
        }else{
            dialog.setProgress(10);
        }
        dialog.show();
    }

    private MyService.MyBinder myBinder;
    private MyService myService;

    private ServiceConnection conn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder= (MyService.MyBinder) service;
            myService=myBinder.getMyService();
            myService.setDownload(new IDownload() {
                @Override
                public void setProgress(int i) {
                    dialog.setProgress(i);
                    //dialog.incrementProgressBy(1);
                    if(i==99){
                        dialog.dismiss();
                    }
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
