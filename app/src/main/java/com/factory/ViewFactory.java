package com.factory;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.view.View;

public class ViewFactory{
    public static <T extends View> T OnCreateView(Activity activity, @IdRes int id, FactoryCallback<T> callback){
        callback.onEvent(activity.findViewById(id));
       return callback.onView(activity,activity.findViewById(id));
    }
    public interface FactoryCallback<T extends View> {
        T onView(Activity activity, T view);
        default void onEvent(T view){};
    }
}
