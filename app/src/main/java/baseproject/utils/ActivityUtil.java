package baseproject.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

/**
 * 修改时间:2018-6-18
 * 修改内容:添加了改变状态栏颜色方法
 */
public class ActivityUtil{
    /**
     * 将px转换为dp
     *
     * @param activity  当前的Activity
     * @param px    像素数
     * @return  返回对应的dp值
     */
    public static int pxTodp(Activity activity,float px){
        final float scale = activity.getResources().getDisplayMetrics().density;
        return (int)(px / scale + 0.5f);
    }

    /**
     * 将dp转换为px
     *
     * @param activity  当前的Activity
     * @param dp    dp值
     * @return  返回像素数
     */
    public static int dipTopx(Activity activity,float dp){
        final float scale = activity.getResources().getDisplayMetrics().density;
        return (int)(dp * scale + 0.5f);
    }

    /**
     * 获取窗口宽度
     *
     * @param activty  当前的Activity
     * @return  返回窗口宽度的像素数
     */
    public static int getWindowWidth(Activity activty){
        WindowManager manager = activty.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获取窗口高度
     *
     * @param activty  当前的Activity
     * @return  返回窗口高度的像素数
     */
    public static int getWindowHeight(Activity activty){
        WindowManager manager = activty.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 设置窗口透明度
     *
     * @param activity  当前的Activity
     * @param alpha     窗口的透明度
     * @param dimbehind     是否让该window后所有的东西都变暗
     */
    public static void setWindowBackgroundAlpha(Activity activity,float alpha,boolean dimbehind){
        if(alpha > 1f || alpha < 0)
            return;

        Window window = activity.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.alpha = alpha;
        if(dimbehind){
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }else{
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
        window.setAttributes(layoutParams);
    }

    /**
     * 设置状态栏颜色,注意需要5.0以上
     * @param activity
     * @param color
     */
    public static void setStatusBarColor(Activity activity,int color){
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(color);
    }
}