package com.factory;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;


public class TabLayoutFactory {
    public static void OnCreate(Context context, TabLayout tabLayout,ResultCallback resultCallback) {
        resultCallback.OnCreateView(context,tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                resultCallback.onTabSelected(context,tab.getText().toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    public interface ResultCallback{
        default void OnCreateView(Context context,TabLayout tabLayout){};
        default void onTabSelected(Context context,String tab){};
    }
}
