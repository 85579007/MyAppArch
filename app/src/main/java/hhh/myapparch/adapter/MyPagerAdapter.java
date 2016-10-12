package hhh.myapparch.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import hhh.myapparch.fragment.AppInfoFragment;
import hhh.myapparch.fragment.SampleRxjavaFragment;

/**
 * Created by hhh on 2016/10/12.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    String[] title={"SampleRxjava","AppInfo"};
    SampleRxjavaFragment sampleRxjavaFragment;
    AppInfoFragment appInfoFragment;

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                sampleRxjavaFragment=new SampleRxjavaFragment();
                return sampleRxjavaFragment;
            case 1:
                appInfoFragment=new AppInfoFragment();
                return appInfoFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
