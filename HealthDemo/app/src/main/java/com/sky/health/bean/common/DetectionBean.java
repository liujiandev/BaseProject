package com.sky.health.bean.common;


import com.sky.health.base.BaseBean;

/**
 * Created by HOME on 2016/11/15.
 */

public class DetectionBean extends BaseBean
{
    private String detectionName;
    private int imgResId;

    public String getDetectionName()
    {
        if(detectionName == null)
        {
            detectionName = "";
        }
        return detectionName;
    }

    public void setDetectionName(String detectionName)
    {
        this.detectionName = detectionName;
    }

    public int getImgResId()
    {
        return imgResId;
    }

    public void setImgResId(int imgResId)
    {
        this.imgResId = imgResId;
    }
}
