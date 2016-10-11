package hhh.myapparch.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by hhh on 2016/10/11.
 */
public abstract class RvCommonAdapter<T> extends RecyclerView.Adapter<RvViewHolder> {
    protected List<T> mDatas;
    protected Context mContext;
    protected int mLayoutId;
    protected LayoutInflater mInflater;

    public RvCommonAdapter(Context mContext, List<T> mDatas, int mLayoutId) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        this.mLayoutId = mLayoutId;
        mInflater=LayoutInflater.from(mContext);
    }

    @Override
    public RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RvViewHolder.get(mContext,parent,mLayoutId);
    }

    @Override
    public void onBindViewHolder(RvViewHolder holder, int position) {
        convert(holder,mDatas.get(position));
    }

    public abstract void convert(RvViewHolder holder, T t);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
