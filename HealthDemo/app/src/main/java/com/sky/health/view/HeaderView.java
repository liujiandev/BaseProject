
package com.sky.health.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

/**
 * HeaderView 标题view
 * Created by lzx on 15-4-24.
 */
public class HeaderView extends LinearLayout {

    private LinearLayout leftView;
    private ImageView leftIcon;
    private TextView leftText;

    private LinearLayout midView;
    private LinearLayout midTextView;
    private TextView midText;
    private TextView midBottomText;
    private ImageView midIcon;

    private LinearLayout rightView;
    private ImageView rightIcon;
    private ImageView rightIcon2;
    private TextView rightText;

    private TextView notifyText;

    private ViewGroup root;
    private Context context;
    private boolean isGetBackEvent = false;//是否需要获取返回按钮点击事件
    private OnClickListener onClickListener;//点击返回按钮监听器

    public HeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setupView(context, attrs);

    }

    private void setupView(Context context, AttributeSet attrs)
    {
        root = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.header_view, null);

        leftView = (LinearLayout) root.findViewById(R.id.ll_hv_left_view);
        leftIcon = (ImageView) root.findViewById(R.id.iv_hv_left_image);
        leftText = (TextView) root.findViewById(R.id.tv_hv_left_text);

        midView = (LinearLayout) root.findViewById(R.id.ll_hv_mid_view);
        midTextView = (LinearLayout) root.findViewById(R.id.ll_hv_mid_text_view);
        midText = (TextView) root.findViewById(R.id.tv_hv_mid_text);
        midBottomText = (TextView) root.findViewById(R.id.tv_hv_mid_bottom_text);
        midIcon = (ImageView) root.findViewById(R.id.iv_hv_mid_image);

        rightView = (LinearLayout) root.findViewById(R.id.ll_hv_right_view);
        rightIcon = (ImageView) root.findViewById(R.id.iv_hv_right_image);
        rightIcon2 = (ImageView) root.findViewById(R.id.iv_hv_right_image2);
        rightText = (TextView) root.findViewById(R.id.tv_hv_right_text);
        notifyText = (TextView) root.findViewById(R.id.notifyText);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderView);

        if (typedArray.hasValue(R.styleable.HeaderView_headerViewLeftIcon)) {
            leftIcon.setVisibility(VISIBLE);
            leftView.setVisibility(View.VISIBLE);
            leftIcon.setImageResource(typedArray.getResourceId(R.styleable.HeaderView_headerViewLeftIcon, R.mipmap.nav_btn_back));
        }

        if (typedArray.hasValue(R.styleable.HeaderView_headerViewLeftText)) {
            leftView.setVisibility(View.VISIBLE);
            leftText.setVisibility(VISIBLE);
            leftText.setText(typedArray.getString(R.styleable.HeaderView_headerViewLeftText));

            if (typedArray.hasValue(R.styleable.HeaderView_headerViewLeftTextSize)) {
                leftText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, typedArray.getDimension(R.styleable.HeaderView_headerViewLeftTextSize, 16));
            }

            if (typedArray.hasValue(R.styleable.HeaderView_headerViewLeftTextColor)) {
                leftText.setTextColor(getResources().getColor(typedArray.getResourceId(R.styleable.HeaderView_headerViewLeftTextColor, R.color.text_white_color)));
            }

        }

        if (typedArray.hasValue(R.styleable.HeaderView_headerViewRightIcon)) {
            rightIcon.setImageResource(typedArray.getResourceId(R.styleable.HeaderView_headerViewRightIcon, android.R.color.transparent));
            rightIcon.setVisibility(VISIBLE);
            rightView.setVisibility(View.VISIBLE);
        }

        if (typedArray.hasValue(R.styleable.HeaderView_headerViewRightIcon2)) {
            rightIcon2.setImageResource(typedArray.getResourceId(R.styleable.HeaderView_headerViewRightIcon2, android.R.color.transparent));
            rightIcon2.setVisibility(VISIBLE);
            rightView.setVisibility(View.VISIBLE);
        }

        if (typedArray.hasValue(R.styleable.HeaderView_headerViewRightText)) {
            rightText.setText(typedArray.getString(R.styleable.HeaderView_headerViewRightText));
            rightView.setVisibility(View.VISIBLE);
            rightText.setVisibility(VISIBLE);
            if (typedArray.hasValue(R.styleable.HeaderView_headerViewRightTextSize)) {
                rightText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, typedArray.getDimension(R.styleable.HeaderView_headerViewRightTextSize, 16));
            }

            if (typedArray.hasValue(R.styleable.HeaderView_headerViewRightTextColor)) {
                rightText.setTextColor(getResources().getColor(typedArray.getResourceId(R.styleable.HeaderView_headerViewRightTextColor, R.color.text_white_color)));
            }
        }


        if (typedArray.hasValue(R.styleable.HeaderView_headerViewMidIcon)) {
            midTextView.setVisibility(GONE);
            midIcon.setVisibility(VISIBLE);
            midIcon.setImageResource(typedArray.getResourceId(R.styleable.HeaderView_headerViewMidIcon, android.R.color.transparent));
        }

        if (typedArray.hasValue(R.styleable.HeaderView_headerViewMidText)) {
            midTextView.setVisibility(VISIBLE);
            midIcon.setVisibility(GONE);
            midText.setVisibility(VISIBLE);
            midText.setText(typedArray.getString(R.styleable.HeaderView_headerViewMidText));

            if (typedArray.hasValue(R.styleable.HeaderView_headerViewMidTextSize)) {
                midText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, typedArray.getDimension(R.styleable.HeaderView_headerViewMidTextSize, 20));
            }

            if (typedArray.hasValue(R.styleable.HeaderView_headerViewMidTextColor)) {
                midText.setTextColor(getResources().getColor(typedArray.getResourceId(R.styleable.HeaderView_headerViewMidTextColor, R.color.text_white_color)));
            }
        }

        if (typedArray.hasValue(R.styleable.HeaderView_headerViewMidBottomText)) {
            midBottomText.setVisibility(VISIBLE);
            midBottomText.setText(typedArray.getString(R.styleable.HeaderView_headerViewMidBottomText));
            if (typedArray.hasValue(R.styleable.HeaderView_headerViewMidBottomTextSize)) {
                midText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, typedArray.getDimension(R.styleable.HeaderView_headerViewMidBottomTextSize, 20));
            }

            if (typedArray.hasValue(R.styleable.HeaderView_headerViewMidBottomTextColor)) {
                midBottomText.setTextColor(getResources().getColor(typedArray.getResourceId(R.styleable.HeaderView_headerViewMidBottomTextColor, (R.color.text_white_color))));
            }

        }

        leftView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isGetBackEvent && onClickListener != null)
                {
                    onClickListener.onClick(v);
                }
                else
                {
                    if (getContext() instanceof Activity) {
                        Intent intent = ((Activity) getContext()).getIntent();
                        ((Activity) getContext()).setResult(Activity.RESULT_OK, intent);
                        ((Activity) getContext()).finish();
                    }
                }
            }
        });

        if (typedArray != null) {
            typedArray.recycle();
        }

        addView(root, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public void setMidText(String s) {
        midText.setVisibility(VISIBLE);
        midText.setText(s);
    }

    public void setleftText(String s) {
        rightView.setVisibility(VISIBLE);
        leftText.setVisibility(View.VISIBLE);
        leftText.setText(s);
    }

    public void setRightText(String s) {
        rightView.setVisibility(VISIBLE);
        rightText.setVisibility(View.VISIBLE);
        rightText.setText(s);
    }

    public TextView getRightText() {
        rightView.setVisibility(VISIBLE);
        rightText.setVisibility(View.VISIBLE);
        return rightText;
    }


    public ImageView getMidIcon() {
        midIcon.setVisibility(VISIBLE);
        return midIcon;
    }

    public void setRightIcon(int imageId) {
        rightIcon.setVisibility(VISIBLE);
        rightView.setVisibility(View.VISIBLE);
        rightIcon.setImageResource(imageId);
    }

    public void setNotifyText(String notify)
    {
        if(notify != null && !notify.equals(""))
        {
            notifyText.setVisibility(VISIBLE);
            notifyText.setText(notify);
        }
        else
        {
            notifyText.setVisibility(View.GONE);
        }
    }

    public void setLeftViewClick(final OnClickListener listener)
    {
        isGetBackEvent = true;
        onClickListener = listener;
    }

    public void setRightViewClick(final OnClickListener listener)
    {
        rightView.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listener.onClick(v);
            }
        });
    }

    public void setRightIconClick(final OnClickListener listener)
    {
        rightIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                listener.onClick(v);
            }
        });
    }

    public void setRightIcon2Click(final OnClickListener listener)
    {
        rightIcon2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                listener.onClick(v);
            }
        });
    }

    public LinearLayout getLeftView() {
        return leftView;
    }

    public LinearLayout getMidView() {
        return midView;
    }

    public LinearLayout getRightView() {
        return rightView;
    }

    public ImageView getRightIcon() {
        return rightIcon;
    }

    public ImageView getRightIcon2() {
        return rightIcon2;
    }
}
