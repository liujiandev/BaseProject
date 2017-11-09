package com.sky.health.interfaces;

import java.util.List;

/**
 * 请求网络数据返回接口
 * Created by HOME on 2016/11/16.
 */
public interface RequestNetworkReturn<T>
{
    /**
     * 请求成功
     * @param resultDate
     */
    void requestSuccess(List<T> resultDate);

    /**
     * 请求失败
     * @param messageError
     */
    void requestFailure(String messageError);

    /**
     * 没有请求到数据
     */
    void requestNoData();
}
