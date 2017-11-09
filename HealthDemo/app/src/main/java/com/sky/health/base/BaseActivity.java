package com.sky.health.base;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;


import com.sky.health.utils.SystemBarTintManager;

import java.util.logging.Logger;

public abstract class BaseActivity extends AppCompatActivity
{

    protected final Logger logger = Logger.getLogger(BaseActivity.class.getSimpleName());

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        AppApplication.getInstance().addActivity(this);
        doOnCreate(savedInstanceState);
    }

    protected abstract void doOnCreate(Bundle savedInstanceState);

    /**
     * 初始化statusBar的背景颜色
     * @param color 背景颜色
     */
    public void initStatusBar(int color)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(color);//通知栏所需颜色
            tintManager.setNavigationBarTintEnabled(true);
        }
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on)
    {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on)
        {
            winParams.flags |= bits;
        }
        else
        {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
