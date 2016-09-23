package hhh.myapparch.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import hhh.myapparch.R;
import hhh.myapparch.fragment.DbFragment;
import hhh.myapparch.fragment.HttpFragment;
import hhh.myapparch.fragment.ImageFragment;

/**
 * Created by hhh on 2016/6/12.
 */
@ContentView(R.layout.activity_home)
public class XutilActivity extends BaseActivity {
    @ViewInject(R.id.container)
    private ViewPager viewPager;
    @ViewInject(R.id.toolbar)
    private Toolbar toolbar;
    @ViewInject(R.id.tabs)
    private TabLayout tabLayout;

    private MyPagerAdapter adapter;

    public static void startActivity(Context context){
        Intent intent=new Intent(context,XutilActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        adapter=new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public class MyPagerAdapter extends FragmentPagerAdapter{

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new HttpFragment();
                case 1:
                    return new DbFragment();
                case 2:
                    return new ImageFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "Http";
                case 1:
                    return "Database";
                case 2:
                    return "Image";
            }
            return null;
        }
    }
}
