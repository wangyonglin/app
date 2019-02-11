package com.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

public class ScreenUtil {
    public static void changeStatus(@NonNull Activity activity){
        DisplayMetrics dm = new DisplayMetrics();

            if(activity.getRequestedOrientation()== ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }else{
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
    }
}
