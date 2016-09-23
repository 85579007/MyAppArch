package hhh.myapparch.helper;

import android.app.Activity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by hhh on 2016/6/16.
 */
public class ActivityManager {
    private static ActivityManager manager=null;
    private Map<String,Activity> map=null;

    private ActivityManager(){
        map=new HashMap<String, Activity>();
    }

    public static  ActivityManager getInstance(){
        if(manager==null){
            manager=new ActivityManager();
        }
        return manager;
    }

    public void put(String string,Activity activity){
        map.put(string,activity);
    }

    public Activity get(String string){
        return map.get(string);
    }

    public boolean isEmpty(){
        return map.isEmpty();
    }

    public int size(){
        return map.size();
    }

    public boolean containName(String string){
        return map.containsKey(string);
    }

    public void closeAll(){
        Set<String> names=map.keySet();
        for(String s:names){
            finishActivity(map.get(s));
        }
        map.clear();;
    }

    public void remove(String name){
        finishActivity(map.get(name));
    }

    private final void finishActivity(Activity activity){
        if(activity!=null&&!activity.isFinishing()){
            activity.finish();
        }
    }
}
