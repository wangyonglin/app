package com.factory;

import android.content.Context;
import android.support.annotation.NonNull;

import android.widget.TextView;



public class ScrollingTextFactory  {
    public static ScrollingTextFactory OnCreate(Context context, TextView textView, ResultCallback callback) {
        callback.onCreateView(context,textView);

        return new ScrollingTextFactory();
    }

    public interface ResultCallback{
       default void onCreateView(@NonNull Context context, @NonNull TextView textView ){};
    }
}
