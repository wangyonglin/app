package com.factory;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;

public interface FactoryCallback<T extends View> {
    default T onView(Activity activity, T view){ return view;};
    default T onView(Context context, T view){ return view;};
    default void onEvent(T view){};
}