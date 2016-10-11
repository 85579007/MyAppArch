package hhh.myapparch.bean;

import android.content.Intent;
import android.graphics.drawable.Drawable;

/**
 * Created by hhh on 2016/10/11.
 */
public class AppInfo implements Comparable<Object> {
    String appLabel;
    String pkgName;
    Drawable appIcon;
    Intent lauchIntnet;

    public AppInfo() {
    }

    public String getAppLabel() {
        return appLabel;
    }

    public void setAppLabel(String appLabel) {
        this.appLabel = appLabel;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public Intent getLauchIntnet() {
        return lauchIntnet;
    }

    public void setLauchIntnet(Intent lauchIntnet) {
        this.lauchIntnet = lauchIntnet;
    }

    @Override
    public int compareTo(Object another) {
        AppInfo f=(AppInfo)another;
        return getAppLabel().compareTo(f.getAppLabel());
    }
}
