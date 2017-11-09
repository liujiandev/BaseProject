package com.sky.health.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.Xfermode;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.sky.health.R;
import com.sky.health.utils.Tools;


/**
 * 自定义圆点组成的进度条
 * Created by HOME on 2016/11/13.
 */
public class DotProgressBar extends View
{
    /**
     * 组件的宽
     */
    private int componentWidth;
    /**
     * 组件的高
     */
    private int componentHeight;
    /**
     * 当前进度值
     */
    private int currentProgress;
    /**
     * 当前刷新的进度值
     */
    private int currentRefreshProgress;
    /**
     * 总的进度值
     */
    private int countProgress;
    /**
     * 圆点进度颜色
     */
    private int dotColor;
    /**
     * 圆点背景颜色
     */
    private int dotBgColor;
    /**
     * 描述文本
     */
    private String describeText;
    /**
     * 描述文本字体颜色
     */
    private int describeColor;
    /**
     * 描述文本字体大小
     */
    private float decribeTextSize;
    /**
     * 使用系统字体，默认为false(使用外部导入字体assets/fonts/HelveticaNeueLTPro.ttf)
     */
    private boolean isFontSystem;
    /**
     *瘦身大小
     */
    private int thinPadding;
    /**
     * 进度值字体大小
     */
    private float progressTextSize;
    /**
     * 进度值字体颜色
     */
    private int progressTextColor;
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 画布
     */
    private Canvas mCanvas;
    /**
     * 所绘制的图片
     */
    private Bitmap mBitmap;
    /**
     * sin(1°)的弧度值
     */
    private float mSin_1; // sin(1°)
    /**
     * 进度值字体样式
     */
    private Typeface mDescribeTypeface;
    private Xfermode mClearCanvasXfermode;
    private Xfermode mPercentThinXfermode;
    /**
     * 按钮按下时的图片
     */
    private Bitmap mBtnPressedBitmap;
    /**
     * 按钮正常时的图片
     */
    private Bitmap btnBitmap;
    /**
     * 大圆圆心X
     */
    private float mCenterX;
    /**
     * 大圆圆心Y
     */
    private float mCenterY;
    /**
     * 按钮矩形范围
     */
    private Rect mBtnBitmapRect;
    /**
     * 是否是按下状态
     */
    private boolean isTouchBtn = false;
    /**
     * 点击事件监听器
     */
    private OnClickListener clickListener;

    private Handler mBackHandler;
    private Runnable mBackRunnable;
    private boolean isProgressGoing;
    public DotProgressBar(Context context)
    {
        this(context,null);
    }

    public DotProgressBar(Context context, AttributeSet attrs)
    {
        this(context, attrs,0);
    }

    public DotProgressBar(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initAttrs(context,attrs);
    }

    /**
     * 初始化属性
     */
    private void initAttrs(Context context,AttributeSet attrs)
    {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DotProgressBar);
        // 获取自定义属性值或默认值
        countProgress = typedArray.getInteger(R.styleable.DotProgressBar_progressMax, 100);
        dotColor = typedArray.getColor(R.styleable.DotProgressBar_dotColor, Color.WHITE);
        dotBgColor = typedArray.getColor(R.styleable.DotProgressBar_dotBgColor, Color.GRAY);

        progressTextSize = typedArray.getDimension(R.styleable.DotProgressBar_progressTextSize, Tools.dp2px(30,context));
        progressTextColor = typedArray.getInt(R.styleable.DotProgressBar_progressTextColor, Color.WHITE);
        isFontSystem = typedArray.getBoolean(R.styleable.DotProgressBar_isFontSystem, false);
        thinPadding = typedArray.getInt(R.styleable.DotProgressBar_thinPadding, 0);

        describeText = typedArray.getString(R.styleable.DotProgressBar_describeText);
        decribeTextSize = typedArray.getDimension(R.styleable.DotProgressBar_describeTextSize, Tools.dp2px(15,context));
        describeColor = typedArray.getInt(R.styleable.DotProgressBar_describeTextColor, Color.GRAY);

        btnBitmap = BitmapFactory.decodeResource(context.getResources(),typedArray.getResourceId(R.styleable.DotProgressBar_btnImage, android.R.color.transparent));
        mBtnPressedBitmap = BitmapFactory.decodeResource(context.getResources(),typedArray.getResourceId(R.styleable.DotProgressBar_btnPressedImage, android.R.color.transparent));
        typedArray.recycle();

        // 其他准备工作
        mSin_1 = (float) Math.sin(Math.toRadians(1.8)); // 求sin(1°)。角度需转换成弧度
        mPaint = new Paint();
        mPaint.setAntiAlias(true); // 消除锯齿

        mDescribeTypeface = isFontSystem ? Typeface.DEFAULT : Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeueLTPro.ttf");
        mClearCanvasXfermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
        if (thinPadding != 0)
        {
            mPercentThinXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        componentWidth = getMeasuredWidth();
        componentHeight = getMeasuredHeight();
        System.out.println("x值"+getX()+"宽："+componentWidth);
        System.out.println("Y值："+getY()+"高 ："+componentHeight);
        System.out.println("cos7.2"+Math.cos(Math.PI/6));
        System.out.println("cos30"+Math.sin(Math.PI/6.0));
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        drawCircleDot(mCanvas);
        drawBtnBitmap(mCanvas);
        drawProgressText(mCanvas);
        drawDescribeText(mCanvas);
        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    /**
     * 绘制描述文本
     * @param canvas
     */
    private void drawDescribeText(Canvas canvas)
    {
        mPaint.setTypeface(mDescribeTypeface);
        mPaint.setColor(describeColor);
        mPaint.setTextSize(decribeTextSize);
        float describeTextWidth = mPaint.measureText(describeText+"");
        mPaint.setTypeface(Typeface.DEFAULT);
        float baseline = 0;
        // 字体在垂直居中时，字体中间就是centerY，加上字体实际高度的一半就是descent线，减去descent就是baseline线的位置（fm中以baseline为基准）
        baseline = getHeight() - decribeTextSize*3;
        mCanvas.drawText(describeText,mCenterX - describeTextWidth / 2, baseline, mPaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                if (isTouchInButton(event.getX(), event.getY()))
                {
                    isTouchBtn = true;
                    postInvalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isTouchBtn)
                {
                    if (!isTouchInButton(event.getX(), event.getY()))
                    {
                        isTouchBtn = false;
                        postInvalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (isTouchInButton(event.getX(), event.getY()) && clickListener != null)
                {
                    clickListener.onClick(this);
                    isTouchBtn = false;
                    postInvalidate();
                }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 是否是按在按钮上
     * @param x
     * @param y
     * @return
     */
    private boolean isTouchInButton(float x, float y)
    {
        // 判断是否在按钮矩形中
        if (x >= mBtnBitmapRect.left && x <= mBtnBitmapRect.right && y >= mBtnBitmapRect.top && y <= mBtnBitmapRect.bottom)
        {
            return true;
        }
        return false;
    }

    /**
     * 绘制按钮
     * @param canvas
     */
    private void drawBtnBitmap(Canvas canvas)
    {
        mBtnBitmapRect = new Rect((int)(mCenterX - (btnBitmap.getWidth()/2)),(btnBitmap.getHeight() / 2),(int)(mCenterX +(btnBitmap.getWidth()/2)),(btnBitmap.getHeight()/2)*3);
        if(isTouchBtn)
        {
            mCanvas.drawBitmap(mBtnPressedBitmap,mCenterX - (mBtnPressedBitmap.getWidth()/2),(mBtnPressedBitmap.getHeight() / 2),mPaint);
        }
        else
        {
            mCanvas.drawBitmap(btnBitmap,mCenterX - (btnBitmap.getWidth()/2),(btnBitmap.getHeight() / 2),mPaint);
        }
    }

    /**
     * 绘制进度值
     * @param canvas
     */
    private void drawProgressText(Canvas canvas)
    {
        // 测量百分比和单位的宽度
        mPaint.setTypeface(mDescribeTypeface);
        mPaint.setTextSize(progressTextSize);
        mPaint.setColor(progressTextColor);
        String progress = Tools.formatFloat_ex(currentProgress / (countProgress*1f) * 100);
        float percentTextWidth = mPaint.measureText(progress + "");
        mPaint.setTypeface(Typeface.DEFAULT);
        float textWidth = percentTextWidth;

        float baseline = 0;
        float textHeight = progressTextSize;
        // 计算Text垂直居中时的baseline
        mPaint.setTextSize(textHeight);
        Paint.FontMetrics fm = mPaint.getFontMetrics();
        // 字体在垂直居中时，字体中间就是centerY，加上字体实际高度的一半就是descent线，减去descent就是baseline线的位置（fm中以baseline为基准）
        baseline = mCenterY + (fm.descent - fm.ascent)/2 - fm.descent;
        mCanvas.drawText(progress + "", mCenterX - textWidth / 2, baseline, mPaint);
    }

    /**
     * 绘制圆点进度
     * @param canvas 画布
     */
    private void drawCircleDot(Canvas canvas)
    {
        // 先清除上次绘制的
        mPaint.setXfermode(mClearCanvasXfermode);
        mCanvas.drawPaint(mPaint);
        mPaint.setXfermode(null);

        // 计算圆点半径
        float outerRadius = (getWidth() < getHeight() ? getWidth() : getHeight()) / 2f;
        mCenterX = componentWidth / 2f;
        mCenterY = componentHeight / 2f;

        // outerRadius = innerRadius + dotRadius
        // sin((360°/200)/2) = sin(0.9°) = dotRadius / innerRadius;
        // 为了让圆点饱满一些，把角度0.9°增加0.1°到1°
        float dotRadius = mSin_1 * outerRadius / (1 + mSin_1);

        // 画进度
        mPaint.setColor(dotColor);
        mPaint.setStyle(Paint.Style.FILL);
        int count = 0;
        // 1.1 当前进度
        while (count++ < currentProgress) {
            mCanvas.drawCircle(mCenterX, mCenterY - outerRadius + dotRadius, dotRadius, mPaint);
            mCanvas.rotate(7.2f, mCenterX, mCenterY);
        }
        // 1.2 未完成进度
        mPaint.setColor(dotBgColor);
        count--;
        while (count++ < countProgress) {
            mCanvas.drawCircle(mCenterX, mCenterY - outerRadius + dotRadius, dotRadius, mPaint);
            mCanvas.rotate(7.2f, mCenterX, mCenterY);
        }
    }

    public synchronized int getCountProgress()
    {
        return countProgress;
    }

    public synchronized void setCountProgress(int countProgress)
    {
        if (countProgress < 0)
        {
            throw new IllegalArgumentException("progressMax mustn't smaller than 0");
        }
        this.countProgress = countProgress;
    }

    public synchronized int getCurrentProgress()
    {
        return currentProgress;
    }

    /**
     * 设置进度
     * 同步，允许多线程访问
     * @param currentProgress 进度
     */
    public synchronized void setCurrentProgress(int currentProgress)
    {
        if (currentProgress < 0 || currentProgress > countProgress)
        {
            throw new IllegalArgumentException(String.format(getResources().getString(R.string.CircleDotProgressBar_progress_out_of_range), countProgress));
        }
        this.currentProgress = currentProgress;
        postInvalidate(); // 可以直接在子线程中调用，而invalidate()必须在主线程（UI线程）中调用
    }

    /**
     * 刷新进度条
     * @param progress
     */
    public void refreshDotProgress(final int progress)
    {
        if(isProgressGoing)
        {
            return;
        }
        isProgressGoing = true;
        currentRefreshProgress = 0;
        clearDelay();
        startDelay(progress);
    }

    public OnClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    //启动延时
    private void startDelay(final int progress)
    {
        mBackHandler = new Handler();
        mBackRunnable = new Runnable()
        {
            public void run()
            {
                if (!isProgressGoing)
                {
                    return;
                }
                if (++currentRefreshProgress >= progress)
                {
                    currentRefreshProgress = progress;
                    setCurrentProgress(progress);
                    stopRefreshProgress();
                    return;
                }
                setCurrentProgress(currentRefreshProgress);
                clearDelay();
                startDelay(progress);
            }
        };
        mBackHandler.postDelayed(mBackRunnable, 100);
    }

    //清延时
    private void clearDelay()
    {
        if((mBackRunnable != null) && (mBackHandler != null))
        {
            mBackHandler.removeCallbacks(mBackRunnable);
        }
        mBackHandler = null;
        mBackRunnable = null;
    }

    /**
     * 停止刷新进度条
     */
    private void stopRefreshProgress()
    {
        isProgressGoing = false;
        clearDelay();
    }
}

