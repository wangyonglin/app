package com.factory;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.view.View;

public class ViewFactory{
    public static <T extends View> T OnCreateView(Context context,View view,@IdRes int id, FactoryCallback<T> callback){
        callback.onEvent(view.findViewById(id));
        return callback.onView(context,view.findViewById(id));
    }
    public static <T extends View> T OnCreateView(Activity activity, @IdRes int id, FactoryCallback<T> callback){
        callback.onEvent(activity.findViewById(id));
       return callback.onView(activity,activity.findViewById(id));
    }

}
