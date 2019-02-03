package com.event;

import android.content.Context;

import android.support.design.widget.TabLayout;
import android.view.View;

public class TabLayoutEvent {
    public static void OnTabSelectedListener(Context context, TabLayout tabLayout, ResultCallback resultCallback){
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                resultCallback.onTabSelected(tab.getText().toString());
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
        default void onTabSelected(String text){};
    }
}