package com.vendor.design;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;

public class MetricsBuilder {
    public  static DisplayMetrics create(Context context, DisplayMetricsCallback callback)
    {
        float density=context.getResources().getDisplayMetrics().density;
        int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        int heightPixels = context.getResources().getDisplayMetrics().heightPixels;
        float xdpi = context.getResources().getDisplayMetrics().xdpi;
        float ydpi = context.getResources().getDisplayMetrics().ydpi;
        callback.metrics(density,widthPixels,heightPixels,xdpi,ydpi);
        return context.getResources().getDisplayMetrics();
    }
    public  static DisplayMetrics created(Activity activity,DisplayMetricsCallback callback)
    {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        callback.metrics(localDisplayMetrics.density,localDisplayMetrics.widthPixels,localDisplayMetrics.heightPixels,localDisplayMetrics.xdpi,localDisplayMetrics.ydpi);
        return localDisplayMetrics;
    }
    public  static int getStatusBarHeight(Activity activity){
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);

        int statusBarHeight = frame.top;
        return statusBarHeight;
    }

    public interface DisplayMetricsCallback{
        void metrics(float density ,int widthPixels,int heightPixels,float xdpi,float ydpi);
    }
}
