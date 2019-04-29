package com.driver;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import javakit.Callback;


public  interface Controller extends View.OnClickListener {
    default void auth(Context context){}
    default void onCreateController(){};
    default void onCreateViewController(Context context,View view){};
    default  void startActivity(Context packageContext, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(packageContext, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        packageContext.startActivity(intent);
    }

}
