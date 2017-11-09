package com.sky.health.interfaces;



/**
 * Created by HOME on 2016/10/27.
 */
public interface RequestReturnListener<T>
{
    /**
     * 返回结果
     * @param result
     */
    void returnResult(T result);

}
