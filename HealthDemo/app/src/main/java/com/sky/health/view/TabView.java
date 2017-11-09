package com.sky.health.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sky.health.R;
import com.sky.health.utils.StringUtils;

/**
 * Created by HOME on 2016/8/15.
 */
public class TabView extends LinearLayout
{

    private Context mContext;
    private ImageView tabIcon;
    private TextView tabText;
    private TextView notifyText;
    private ViewGroup viewGroup;
    private int tabNormalIcon;
    private int tabSelectedIcon;
    private int tabTextNormalColor;
    private int tabTextSelectedColor;
    private int tabNormalBgColor;
    private int tabSelectedBgColor;
    public TabView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.mContext = context;
        initView(context,attrs);
    }

    /**
     * 初始化view
     * @param context
     * @param attrs
     */
    private void initView(Context context, AttributeSet attrs)
    {
        viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.tab_view,null);
        tabIcon = (ImageView) viewGroup.findViewById(R.id.tabIcon);
        tabText = (TextView) viewGroup.findViewById(R.id.tabText);
        notifyText = (TextView) viewGroup.findViewById(R.id.notifyText);
        initAttrs(attrs);
        unSelected();
    }

    /**
     * 初始化组件属性
     * @param attrs
     */
    private void initAttrs(AttributeSet attrs)
    {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.TabViewAttribute);
        /**
         * 字体文本
         */
        if (typedArray.hasValue(R.styleable.TabViewAttribute_tabText))
        {
            tabText.setText(typedArray.getString(R.styleable.TabViewAttribute_tabText));
        }
        /**
         * 字体大小
         */
        if (typedArray.hasValue(R.styleable.TabViewAttribute_tabTextSize))
        {
            tabText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, typedArray.getDimension(R.styleable.TabViewAttribute_tabTextSize, 12));
        }
        /**
         * 正常图标
         */
        if (typedArray.hasValue(R.styleable.TabViewAttribute_tabNormalIcon))
        {
           tabNormalIcon = typedArray.getResourceId(R.styleable.TabViewAttribute_tabNormalIcon, android.R.color.transparent);
        }
        /**
         * 选中图标
         */
        if (typedArray.hasValue(R.styleable.TabViewAttribute_tabSelectedIcon))
        {
            tabSelectedIcon = typedArray.getResourceId(R.styleable.TabViewAttribute_tabSelectedIcon, android.R.color.transparent);
        }
        /**
         * 正常字体颜色
         */
        if (typedArray.hasValue(R.styleable.TabViewAttribute_tabTextNormalColor))
        {
            tabTextNormalColor = getResources().getColor(typedArray.getResourceId(R.styleable.TabViewAttribute_tabTextNormalColor, R.color.text_white_color));
        }
        /**
         * 选中字体颜色
         */
        if (typedArray.hasValue(R.styleable.TabViewAttribute_tabTextSelectedColor))
        {
            tabTextSelectedColor = getResources().getColor(typedArray.getResourceId(R.styleable.TabViewAttribute_tabTextSelectedColor, R.color.text_white_color));
        }
        /**
         * 正常背景颜色
         */
        if (typedArray.hasValue(R.styleable.TabViewAttribute_tabNormalBgColor))
        {
            tabNormalBgColor = typedArray.getResourceId(R.styleable.TabViewAttribute_tabNormalBgColor, android.R.color.transparent);
        }
        /**
         * 选中背景颜色
         */
        if (typedArray.hasValue(R.styleable.TabViewAttribute_tabSelectedBgColor))
        {
            tabSelectedBgColor = typedArray.getResourceId(R.styleable.TabViewAttribute_tabSelectedBgColor, android.R.color.transparent);
        }
        if (typedArray != null)
        {
            typedArray.recycle();
        }
        addView(viewGroup, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    /**
     * 选中
     */
    public void selected()
    {
        tabIcon.setImageResource(tabSelectedIcon);
        tabText.setTextColor(tabTextSelectedColor);
        viewGroup.setBackgroundResource(tabSelectedBgColor);
    }

    /**
     * 未选中
     */
    public void unSelected()
    {
        tabIcon.setImageResource(tabNormalIcon);
        tabText.setTextColor(tabTextNormalColor);
        viewGroup.setBackgroundResource(tabNormalBgColor);
    }

    /**
     * 设置notifyText文本
     * @param text
     */
    public void setNotifyText(String text)
    {
        if(text != null && StringUtils.isNumeric(text) && !text.toString().trim().equals("0"))
        {
            notifyText.setText(text);
            notifyText.setVisibility(View.VISIBLE);
        }
        else
        {
            notifyText.setVisibility(View.GONE);
        }
    }
}
