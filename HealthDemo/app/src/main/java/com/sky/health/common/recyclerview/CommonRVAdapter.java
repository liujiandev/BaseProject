package com.sky.health.common.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by liuJian on 2016/8/4.
 */
public abstract class CommonRVAdapter<T> extends RecyclerView.Adapter<RVViewHolder>
{
    private Context mContext;
    private List<T> mDatas;
    private int mLayoutId;
    public CommonRVAdapter(Context context, int layoutId)
    {
        this.mContext = context;
        this.mLayoutId = layoutId;
    }

    public CommonRVAdapter(Context context, int mLayoutId, List<T> data)
    {
        this.mContext = context;
        this.mLayoutId = mLayoutId;
        this.mDatas = data;
    }

    public void setDatas(List<T> mDatas)
    {
        this.mDatas = mDatas;
    }

    public List<T> getDatas() {
        return mDatas;
    }

    @Override
    public RVViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        RVViewHolder viewHolder = RVViewHolder.createViewHolder(mContext,parent,mLayoutId);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RVViewHolder holder, int position)
    {
        convert(holder,mDatas.get(position));
    }

    @Override
    public int getItemCount()
    {
        return mDatas == null ? 0 : mDatas.size();
    }

    public abstract void convert(RVViewHolder holder,T t);
}
