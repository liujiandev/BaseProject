package com.sky.health.base;
import android.app.Activity;
import android.app.Application;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by tony on 16/4/21.
 */
public class AppApplication extends Application
{
    private static AppApplication application;
    private List<Activity> activitys;
    public static synchronized AppApplication getInstance()
    {
        if(application == null)
        {
            application = new AppApplication();
        }
        return application;
    }

    public AppApplication()
    {
        super();
        activitys = new ArrayList<Activity>();
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    /**
     * 添加activity
     * @param activity
     */
    public void addActivity(Activity activity)
    {
        activitys.add(activity);
    }

    /**
     * 将activity都清除
     * @param iskill   true 结束整个应用
     * 在播放音乐的界面回到桌面，然后被回收了，退出的时候，会再次运行程序
     */
    public void exit(boolean iskill)
    {
        for(Activity a : activitys)
        {
            if(a != null)
                a.finish();
        }
        if(iskill)
        {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
    }
}
