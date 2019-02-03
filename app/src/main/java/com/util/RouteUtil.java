package com.util;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.driver.PreloaderService;

import vendor.android.ReceiverRoute;

public class RouteUtil extends ReceiverRoute {
    public static Intent createService(Context context){
        Intent intent = new Intent(context, PreloaderService.class);
        context.startService(intent);
        return intent;
    }
    public static Intent destroService(Context context){
        Intent intent = new Intent(context, PreloaderService.class);
        context.stopService(intent);
        return intent;
    }
    public static void JumpWhenCanClick(Context packageContext, Class<?> cls,Intent intent) {
        intent.setClass(packageContext, cls);
        packageContext.startActivity(intent);
    }
    public static void JumpWhenCanClick(Context packageContext, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(packageContext, cls);
        packageContext.startActivity(intent);
    }
    public static void JumpWhenCanClick(Context packageContext, Class<?> cls,String uuid) {
       Intent intent= ParamUtil.push(packageContext,cls,uuid);
        packageContext.startActivity(intent);
    }

    public interface BuilderCallback{
        public void put(Bundle bundle);
    }
}
