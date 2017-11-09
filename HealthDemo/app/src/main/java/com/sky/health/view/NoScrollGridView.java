
package com.sky.health.view;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

public class NoScrollGridView extends GridView {

    public NoScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mExpandSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {
        if(ev.getAction() == MotionEvent.ACTION_MOVE)//滑动
        {
            System.out.println("================NoScrollGridView================");
            return false;
        }
        return super.onTouchEvent(ev);
    }
}