package com.sky.health.adapter.common;

import android.content.Context;
import android.view.View;

import com.sky.health.R;
import com.sky.health.bean.common.DetectionBean;
import com.sky.health.common.recyclerview.RVViewHolder;
import com.sky.health.interfaces.RequestReturnListener;

/**
 * Created by HOME on 2016/11/16.
 */

public class PullZoomRecyclerAdapter extends ZoomRecyclerAdapter<DetectionBean>
{
    private RequestReturnListener<DetectionBean> requestReturnListener;
    public PullZoomRecyclerAdapter(Context context, int layoutId)
    {
        super(context, layoutId);
    }

    @Override
    public void convert(RVViewHolder holder, final DetectionBean detectionBean)
    {
        if(detectionBean.getImgResId() != -1)
        {
            holder.setText(R.id.itemName,detectionBean.getDetectionName());
            holder.setImageResource(R.id.itemIcon,detectionBean.getImgResId());
            holder.setOnItemClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    if(requestReturnListener != null)
                    {
                        requestReturnListener.returnResult(detectionBean);
                    }
                }
            });
        }
    }

    public RequestReturnListener<DetectionBean> getRequestReturnListener() {
        return requestReturnListener;
    }

    public void setRequestReturnListener(RequestReturnListener<DetectionBean> requestReturnListener) {
        this.requestReturnListener = requestReturnListener;
    }
}
