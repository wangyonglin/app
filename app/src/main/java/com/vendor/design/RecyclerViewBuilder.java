package com.vendor.design;

import android.app.ActionBar;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.khbd.app.R;

import vendor.android.framework.FrameworkUtil;

public class RecyclerViewBuilder {


    public static RecyclerViewBuilder create(Context context, RecyclerView view, BuilderCallback callback){
        callback.onCreateView(context,view);
        return new RecyclerViewBuilder();
    }


    public interface BuilderCallback{
        default void onCreateView(Context context,RecyclerView view){};
    }
}
