package com.sky.health.base;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.view.View;

import com.sky.health.utils.SystemBarTintManager;

/**
 * Created by HOME on 2016/11/14.
 */
public class BaseFragment extends Fragment
{
    public void setPadding(View view)
    {
        //设置系统栏需要的内偏移
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + SystemBarTintManager.getStatusHeight(getActivity()), view.getPaddingRight(), view.getPaddingBottom());
        }
    }
}
