package com.driver;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import javakit.Callback;

public class SystemInfo{
    public static void Version(Context context,Callback<PackageInfo> callback){
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            callback.resove(info);
        } catch (PackageManager.NameNotFoundException e) {
            callback.reject(e);
        }
    }

}
