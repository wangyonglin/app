package com.vendor.design;


import android.graphics.Color;
import android.support.v7.widget.Toolbar;

import android.view.View;

import com.khbd.app.R;

public class ToolbarBuilder {
    public static void create( Toolbar toolbar,Toolbar.OnMenuItemClickListener listener){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        toolbar.setTitle("葵花宝典 电影APP");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitle("官网:EEOB.COM 微信:EX7132");
        toolbar.setSubtitleTextColor(Color.WHITE);

        //设置menu
        toolbar.inflateMenu(R.menu.menu_item);
        // 设置溢出菜单的图标
        toolbar.setOverflowIcon(toolbar.getResources().getDrawable(R.mipmap.icon_menu));
        // 设置menu item 点击事件
        toolbar.setOnMenuItemClickListener(listener);
    }
}
