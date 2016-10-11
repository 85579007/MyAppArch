package hhh.myapparch.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.blankj.utilcode.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hhh.myapparch.R;
import hhh.myapparch.adapter.CommonAdapter;
import hhh.myapparch.adapter.RvCommonAdapter;
import hhh.myapparch.adapter.RvViewHolder;
import hhh.myapparch.adapter.ViewHolder;
import hhh.myapparch.bean.AppInfo;
import hhh.myapparch.utils.T;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hhh on 2016/10/9.
 */
public class AppInfoActivity extends BaseActivity {
    @BindView(R.id.fragment_recycler_list)
    RecyclerView fragmentRecyclerList;
    @BindView(R.id.fragment_recycler_swipe)
    SwipeRefreshLayout fragmentRecyclerSwipe;

    RvCommonAdapter<AppInfo> adapter;
    List<AppInfo> apps = new ArrayList<AppInfo>();

    public static void startAcitvity(Context context) {
        Intent intent = new Intent(context, AppInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_recycler);
        ButterKnife.bind(this);

        initlist();
    }

    private void initlist() {
        adapter=new RvCommonAdapter<AppInfo>(this,apps,R.layout.item_appinfo) {
            @Override
            public void convert(RvViewHolder helper, AppInfo item) {
                if(item!=null){
                    TextView label=helper.getView(R.id.appinfo_label);
                    TextView pkg=helper.getView(R.id.appinfo_pkg);
                    ImageView iv=helper.getView(R.id.appinfo_iv);
                    label.setText(item.getAppLabel());
                    pkg.setText(item.getPkgName());
                    //iv.setImageBitmap(Bitmap);
                    iv.setImageBitmap(ImageUtils.drawable2Bitmap(item.getAppIcon()));
                }
            }
        };
        fragmentRecyclerList.setAdapter(adapter);
        LinearLayoutManager llm=new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        fragmentRecyclerList.setLayoutManager(llm);
        fragmentRecyclerSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshTheList();
            }
        });
    }

    private void refreshTheList(){
        getApps().toSortedList()
                .subscribe(new Subscriber<List<AppInfo>>() {
                    @Override
                    public void onCompleted() {
                        T.show(getApplicationContext(),"app list show completed"+apps.size());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        T.show(getApplicationContext(),e.toString());
                        fragmentRecyclerSwipe.setRefreshing(false);
                    }

                    @Override
                    public void onNext(List<AppInfo> appInfos) {
                        fragmentRecyclerList.setVisibility(View.VISIBLE);
                        apps.addAll(appInfos);
                        fragmentRecyclerSwipe.setRefreshing(false);
                    }
                });
    }

    private Observable<AppInfo> getApps() {
        return Observable.create(new Observable.OnSubscribe<AppInfo>() {
            @Override
            public void call(Subscriber<? super AppInfo> subscriber) {
                PackageManager pm = getApplication().getPackageManager();

                Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
                mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                List<ResolveInfo> infos = pm.queryIntentActivities(mainIntent, PackageManager.MATCH_DEFAULT_ONLY);

                for (ResolveInfo info : infos) {
                    String act = info.activityInfo.name;
                    String pkg = info.activityInfo.packageName;
                    String label = (String) info.loadLabel(pm);
                    Drawable icon = info.loadIcon(pm);

                    Intent launch = new Intent();
                    launch.setComponent(new ComponentName(pkg, act));
                    AppInfo appInfo = new AppInfo();
                    appInfo.setAppLabel(label);
                    appInfo.setPkgName(pkg);
                    appInfo.setAppIcon(icon);
                    appInfo.setLauchIntnet(launch);

                    //apps.add(appInfo);
                    if (subscriber.isUnsubscribed()) {
                        return;
                    }
                    subscriber.onNext(appInfo);
                }

                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
            }
        });
    }
}
