package com.view;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.khbd.app.R;

public class ToolbarFactory {
    public static void OnCreate(Context context,Toolbar toolbar, ResultCallback resultCallback){
        if(toolbar==null){
            throw new RuntimeException("Stub!");
        }
        toolbar.setTitle("葵花宝典 电影APP");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitle("官网:EEOB.COM 微信:EX7132");
        toolbar.setSubtitleTextColor(Color.WHITE);

        //设置menu
        toolbar.inflateMenu(R.menu.menu_item);
        // 设置溢出菜单的图标
        toolbar.setOverflowIcon(toolbar.getResources().getDrawable(R.mipmap.icon_menu));
        // 设置menu item 点击事件
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.toolbar_item_share:
                        resultCallback.onShare();
                        break;

                }
                return false;
            }
        });
    }
    public interface ResultCallback{
        default void OnCreateView(Context context,BottomNavigationView view){};
        default void onShare(){};
    }
}
