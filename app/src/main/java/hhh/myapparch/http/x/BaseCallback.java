package hhh.myapparch.http.x;

import org.xutils.common.Callback.CommonCallback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import hhh.myapparch.dialog.Loading;
import hhh.myapparch.log.MyLog;
import hhh.myapparch.utils.JsonUtils;

/**
 * Created by hhh on 2016/6/12.
 */
public abstract class BaseCallback<T> implements CommonCallback<String> {
    private Type type;

    public BaseCallback(){
        Type superClass=this.getClass().getGenericSuperclass();
        this.type=((ParameterizedType)superClass).getActualTypeArguments()[0];
    }

    @Override
    public void onSuccess(String result) {
        Loading.dismiss();;
        if(result!=null){
            MyLog.LogWithString(result);
            if(result.matches("^\\{.*\\}$")){
                T data= JsonUtils.fromJson(result,type);
                if(data!=null) {
                    success(data);
                }else{
                    XUtils.show("返回数据空");
                }
            }else {
                XUtils.show("数据格式错误");
            }
        }else{
            XUtils.show("数据加载失败");
        }
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {

    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }

    public abstract void success(T data);
}
