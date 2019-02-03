package com.factory;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;


public class TabLayoutFactory {
    public static void OnCreate(Context context, TabLayout tabLayout,ResultCallback resultCallback) {
        resultCallback.OnCreateView(context,tabLayout);
    }
    public interface ResultCallback{
        default void OnCreateView(Context context,TabLayout tabLayout){};
    }
}
