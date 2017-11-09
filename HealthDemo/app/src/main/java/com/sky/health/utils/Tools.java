package com.sky.health.utils;

import android.content.Context;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 工具
 * Created by HOME on 2016/11/13.
 */
public class Tools
{
    /**
     * dip 转换为 px
     * @param dp
     * @param context
     * @return
     */
    public static int dp2px(int dp, Context context)
    {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density + .5f);
    }

    /**
     * 像素 转成 dip
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * px 转成 sp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue)
    {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static String formatThreeFloat(float val)
    {
        DecimalFormat df = new DecimalFormat("########.000");
        String ret = "0";
        try {
            ret = String.valueOf(Float.parseFloat(df.format(val)));
            String[] ar = ret.split("\\.");
            if (ar.length == 1) {
                ret = ret + ".000";
            } else {
                int length = ar[1].length();
                if (length == 1) {
                    ret = ret + "00";
                } else if (length == 2) {
                    ret = ret + "0";
                }
            }
        } catch (Exception e) {
            // 不退出
        }
        return ret;
    }

    public static String formatFloat_ex2(float val)
    {
        DecimalFormat df = new DecimalFormat("########.00");
        String ret = String.valueOf(Float.parseFloat(df.format(val / 100)));
        String[] ar = ret.split("\\.");
        if (ar.length == 1) {
            ret = ret + ".00";
        } else {
            if (ar[1].length() == 1)
            {
                ret = ret + "0";
            }
        }
        return ret;
    }

    public static String formatFloat_ex0(float val)
    {
        DecimalFormat df = new DecimalFormat("########.00");
        float v = Float.parseFloat(df.format(val / 100));
        int iv = (new BigDecimal(v).setScale(0, BigDecimal.ROUND_HALF_UP)).intValue();
        String ret = String.valueOf(iv);
        return ret;
    }

    public static String formatFloat_ex(float val)
    {
        DecimalFormat df = new DecimalFormat("########.000");
        String ret = String.valueOf(Float.parseFloat(df.format(val)));
        String[] ar = ret.split("\\.");
        if (ar[1].equals("0")) {
            ret = ret.replace(".0", "");
        }
        return ret;
    }

    public static String formatFloat_ex4(float val)
    {
        DecimalFormat df = new DecimalFormat("########.0000");
        return String.valueOf(Float.parseFloat(df.format(val)));
    }
}
