package hhh.myapparch.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hhh on 2016/10/11.
 */
public class RvViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private Context mContext;
    private View mConverView;

    public RvViewHolder(Context context,View itemView) {
        super(itemView);
        mConverView=itemView;
        mContext=context;
        mViews=new SparseArray<View>();
    }

    public static RvViewHolder get(Context context, ViewGroup parent,int layoutId){
        View item= LayoutInflater.from(context).inflate(layoutId,parent,false);
        return new RvViewHolder(context,item);
    }

    public <T extends View> T getView(int viewId){
        View view=mViews.get(viewId);
        if(view==null){
            view=mConverView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }
}
