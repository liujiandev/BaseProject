package com.sky.health.common.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Created by liuJian on 2016/8/4.
 */
public class RVItemDecoretion extends RecyclerView.ItemDecoration
{
    private Context mContext;
    private static final int HORIZONTAL = LinearLayoutManager.HORIZONTAL;
    private static final int VERTICAL = LinearLayoutManager.VERTICAL;
    private int mOrientation = VERTICAL;
    private Drawable divier;
    /**
     * RVItemDecoretion 构造
     * @param context
     * @param orientation
     */
    public RVItemDecoretion(Context context, int orientation, int attrsId)
    {
        this.mContext = context;
        int[] attrs = new int[]{attrsId};
        TypedArray typedArray = context.obtainStyledAttributes(attrs);
        divier = typedArray.getDrawable(0);
        typedArray.recycle();
        setOrientation(orientation);
    }

    /**
     * 绘制分割线
     * @param c 画布
     * @param parent
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent)
    {
        super.onDraw(c, parent);
        if(mOrientation == HORIZONTAL)
        {
            drawHorizontal(c,parent);
        }
        else if(mOrientation == VERTICAL)
        {
            drawVerical(c,parent);
        }
    }

    /**
     * 绘制竖直方向的分割线
     * @param c
     * @param parent
     */
    private void drawVerical(Canvas c, RecyclerView parent)
    {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + divier.getIntrinsicHeight();
            divier.setBounds(left, top, right, bottom);
            divier.draw(c);
        }
    }

    /**
     * 绘制水平方向的分割线
     * @param c
     * @param parent
     */
    private void drawHorizontal(Canvas c, RecyclerView parent)
    {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child .getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + divier.getIntrinsicHeight();
            divier.setBounds(left, top, right, bottom);
            divier.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent)
    {
        if (mOrientation == VERTICAL)
        {
            outRect.set(0, 0, 0, divier.getIntrinsicHeight());
        }
        else
        {
            outRect.set(0, 0, divier.getIntrinsicWidth(), 0);
        }
    }

    /**
     * 设置绘制方向
     * @param orientation 方向
     */
    public void setOrientation(int orientation)
    {
        if (orientation != HORIZONTAL && orientation != VERTICAL)
        {
            throw new IllegalArgumentException("invalid orientation");
        }
        this.mOrientation = orientation;
    }
}
