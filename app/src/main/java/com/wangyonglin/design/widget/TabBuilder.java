package com.wangyonglin.design.widget;

import android.content.Context;
import android.support.design.widget.TabLayout;

import com.heylyn.callback.ResultFouder;


import java.util.ArrayList;
import java.util.List;




public class TabBuilder {
    private List<String> list= new ArrayList<String>();
    public static void create(Context context, TabLayout tabLayout, final ResultFouder<String> callback){
        tabLayout.addTab(tabLayout.newTab().setText("推荐"));
        tabLayout.addTab(tabLayout.newTab().setText("剧情"));
        tabLayout.addTab(tabLayout.newTab().setText("喜剧"));
        tabLayout.addTab(tabLayout.newTab().setText("恐怖"));
        tabLayout.addTab(tabLayout.newTab().setText("战争"));
        tabLayout.addTab(tabLayout.newTab().setText("武侠"));
        tabLayout.addTab(tabLayout.newTab().setText("动作"));
        tabLayout.addTab(tabLayout.newTab().setText("爱情"));
        tabLayout.addTab(tabLayout.newTab().setText("犯罪"));
        tabLayout.addTab(tabLayout.newTab().setText("文艺"));
        tabLayout.addTab(tabLayout.newTab().setText("惊悚"));
        tabLayout.addTab(tabLayout.newTab().setText("科幻"));
        tabLayout.addTab(tabLayout.newTab().setText("伦理"));
        tabLayout.addTab(tabLayout.newTab().setText("历史"));


        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               // callback.loader(tab.getText().toString());
                callback.success(tab.getText().toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
