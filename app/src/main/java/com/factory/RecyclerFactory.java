package com.factory;

import android.content.Context;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.RecyclerView;

public class RecyclerFactory {
    public static void OnCreate(Context context, RecyclerView recyclerView, ResultCallback resultCallback) {
    resultCallback.OnCreateView(context,recyclerView);
    }
    public interface ResultCallback{
        default void OnCreateView(Context context,RecyclerView recyclerView){};
    }
}
