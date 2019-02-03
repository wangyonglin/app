package com.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ParamUtil {
    private static String extra="data";
    public static Intent push(Context packageContext,Class<?> cls,String uuid){
        Intent intent = new Intent();
        intent.setClass(packageContext, cls);
        Bundle bundle = new Bundle();
        bundle.putString("uuid", uuid);  //把数据捆设置改意图
        intent.putExtra("data", bundle);  //激活意图

     return intent;
    }
    public static void pop( Activity activity,ParamCallback callback){
        //获取数据
        Intent intent = activity.getIntent();
        //从intent取出bundle
        Bundle bundle = intent.getBundleExtra(extra);
        //获取数据
        callback.get(bundle.getString("uuid"));
    }
    public interface ParamCallback{
        default void get(String uuid){};
    }
}
