package com.vendor.design.tablayout;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutCategories {
    private Context context;
    private TabLayout tabs;

    private TabLayoutCategories(Context con, TabLayout tab){
        context=con;
        tabs=tab;
    }
    public static TabLayoutCategories Create(Context context, TabLayout tabLayout){
        return new TabLayoutCategories(context,tabLayout);
    }
    public void InitData(){
        tabs.addTab(tabs.newTab().setText("推荐"));
        tabs.addTab(tabs.newTab().setText("剧情"));
        tabs.addTab(tabs.newTab().setText("喜剧"));
    }
}
