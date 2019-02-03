package com.vendor.design;

import android.app.Activity;
import android.content.Context;
import android.view.View;

public class NavigationbarBuilder {

    public static NavigationbarBuilder create(Activity activity,BuilderCallback callback){
        View decorView = activity.getWindow().getDecorView();
        decorView.setSystemUiVisibility(callback.run());
        return new NavigationbarBuilder();
    }
public interface BuilderCallback{
        public int run();
}
}
