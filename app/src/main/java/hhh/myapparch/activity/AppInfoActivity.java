package hhh.myapparch.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;

import butterknife.BindView;
import butterknife.ButterKnife;
import hhh.myapparch.R;
import hhh.myapparch.adapter.MyPagerAdapter;

/**
 * Created by hhh on 2016/10/9.
 */
public class AppInfoActivity extends BaseFragmentActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    public static void startAcitvity(Context context) {
        Intent intent = new Intent(context, AppInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        ButterKnife.bind(this);

        initToolbar();
        init();
    }

    private void init() {
        viewpager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabs.setViewPager(viewpager);
    }

    private void initToolbar() {
        //toolbar.setTitle("MyAppArch");
        //setSupportActionBar(toolbar);

        toolbar.inflateMenu(R.menu.toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.tb_search:
                        break;
                    case R.id.tb_notify:
                        break;
                    case R.id.item1:
                        break;
                    case R.id.item2:
                        break;
                }
                return true;
            }
        });
    }
}
