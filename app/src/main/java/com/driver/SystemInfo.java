package com.driver;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class SystemInfo{
    public static void PackageCallback(Context context,Callback<PackageInfo> callback){
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            callback.call(info);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
    public interface Callback<T extends PackageInfo>{
        public void call(T x);
        default void err(Exception e){};
    }
}
