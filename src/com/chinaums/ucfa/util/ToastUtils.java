package com.chinaums.ucfa.util;

import android.content.Context;
import android.widget.Toast;

/**
 * ToastUtils
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-12-9
 */
public class ToastUtils {

    private ToastUtils() {
        throw new AssertionError();
    }

    /**
     * show a toast
     * 
     * @param context
     * @param resId
     */
    public static void show(Context context, int resId) {
        show(context, context.getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    /**
     * show a toast
     * 
     * @param context
     * @param resId
     * @param duration
     */
    public static void show(Context context, int resId, int duration) {
        show(context, context.getResources().getText(resId), duration);
    }

    /**
     * show a toast
     * 
     * @param context
     * @param text
     */
    public static void show(Context context, CharSequence text) {
        show(context, text, Toast.LENGTH_SHORT);
    }

    /**
     * show a toast
     * 
     * @param context
     * @param text
     * @param duration
     */
    public static void show(Context context, CharSequence text, int duration) {
        Toast.makeText(context, text, duration).show();
    }

    /**
     * show a toast
     * 
     * @param context
     * @param resId
     * @param args
     */
    public static void show(Context context, int resId, Object... args) {
        show(context, String.format(context.getResources().getString(resId), args), Toast.LENGTH_SHORT);
    }

    /**
     * show a toast
     * 
     * @param context
     * @param format
     * @param args
     */
    public static void show(Context context, String format, Object... args) {
        show(context, String.format(format, args), Toast.LENGTH_SHORT);
    }

    /**
     * show a toast
     * 
     * @param context
     * @param resId
     * @param duration
     * @param args
     */
    public static void show(Context context, int resId, int duration, Object... args) {
        show(context, String.format(context.getResources().getString(resId), args), duration);
    }

    /**
     * show a toast
     * 
     * @param context
     * @param format
     * @param duration
     * @param args
     */
    public static void show(Context context, String format, int duration, Object... args) {
        show(context, String.format(format, args), duration);
    }
}
