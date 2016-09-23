package hhh.myapparch.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import hhh.myapparch.R;

/**
 * Created by hhh on 2016/6/19.
 */
public class ViewHolder {
    private final SparseArray<View> mViews;
    private View mConverView;

    private ViewHolder(Context context, ViewGroup parent,int layoutId,int position) {
        this.mViews = new SparseArray<View>();
        mConverView= LayoutInflater.from(context).inflate(layoutId,parent,false);
        mConverView.setTag(this);
    }

    public static ViewHolder get(Context context,View convertView,ViewGroup parent,int layoutId,int position){
        if(convertView==null){
            return new ViewHolder(context,parent,layoutId,position);
        }
        return (ViewHolder) convertView.getTag();
    }

    public <T extends View> T getView(int viewId){
        View v=mViews.get(viewId);
        if(v==null){
            v=mConverView.findViewById(viewId);
            mViews.put(viewId,v);
        }
        return (T)v;
    }

    public View getConvertView(){
        return mConverView;
    }

    public ViewHolder setImageResource(int viewId,int drawable){
        ImageView view=getView(viewId);
        view.setImageResource(drawable);
        return this;
    }

    public ViewHolder setImageBitmap(int viewId,Bitmap bm){
        ImageView view=getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }
    public ViewHolder setText(int viewId,String text){
        TextView view=getView(viewId);
        view.setText(text);
        return this;
    }
}
