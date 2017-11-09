package com.sky.health.activity.loading;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.sky.health.R;
import com.sky.health.activity.main.MainTabActivity;
import com.sky.health.base.BaseActivity;

/**
 * 引导页（起始页）
 */
public class LoadingActivity extends BaseActivity implements ValueAnimator.AnimatorUpdateListener {
    private ImageView mImageView;
    @Override
    protected void doOnCreate(Bundle savedInstanceState)
    {
        initStatusBar(R.color.transparent);
        setContentView(R.layout.activity_loading);
        initView();
    }

    /**
     * 初始化组件view
     */
    private void initView()
    {
        mImageView = (ImageView) findViewById(R.id.mImageView);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mImageView,"alpha",0f,1);
        objectAnimator.setDuration(5000);
        objectAnimator.addUpdateListener(this);
        objectAnimator.start();

    }

    /**
     * 初始化组件点击事件监听
     */
    private void initListener()
    {

    }

    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator)
    {
        float progressValue = (float) valueAnimator.getAnimatedValue();
        if(progressValue == 1)
        {
            Intent intent = new Intent(LoadingActivity.this, MainTabActivity.class);
            startActivity(intent);
        }
    }
}
