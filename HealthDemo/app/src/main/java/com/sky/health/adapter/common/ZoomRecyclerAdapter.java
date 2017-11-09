package com.sky.health.adapter.common;


import android.content.Context;
import android.view.ViewGroup;


import com.sky.health.common.recyclerview.RVViewHolder;

import java.util.List;

/**
 * Created by HOME on 2016/11/15.
 */

public abstract class ZoomRecyclerAdapter<T> extends RecyclerViewHeaderAdapter<RVViewHolder>
{
    private List<T> detectionBeanList;
    private Context mContext;
    private int mLayoutId;
    public ZoomRecyclerAdapter(Context context, int layoutId)
    {
        super(context);
        this.mContext = context;
        this.mLayoutId = layoutId;
    }

    @Override
    public int getCount()
    {
        return getDetectionBeanList() == null?0:getDetectionBeanList().size();
    }

    @Override
    public RVViewHolder onCreateContentView(ViewGroup parent, int viewType)
    {
        RVViewHolder viewHolder = RVViewHolder.createViewHolder(mContext,parent,mLayoutId);
        return viewHolder;
    }

    @Override
    public void onBindView(RVViewHolder holder, int position)
    {
        convert(holder,getDetectionBeanList().get(position));
    }

    public abstract void convert(RVViewHolder holder,T t);

    public List<T> getDetectionBeanList()
    {
        return detectionBeanList;
    }

    public void setDetectionBeanList(List<T> detectionBeanList)
    {
        this.detectionBeanList = detectionBeanList;
    }
}
