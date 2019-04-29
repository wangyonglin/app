package com.factory;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;


import com.kernel.Component;
import com.khbd.app.R;

import org.apache.commons.math3.optim.InitialGuess;

import javakit.result.ResultCallback;
import okhttp3.Request;

public class ToolbarFactory {
    private static View.OnClickListener onClickListener;
    private static Toolbar toolbar;
    private static ResultCallback<Toolbar> resultCallback;

    public static void OnCreate(Context context, Toolbar toolbar, ResultCallback<Toolbar> resultCallback){
        if(toolbar==null){
            throw new RuntimeException("Stub!");
        }

       toolbar.setTitle("Aibaonv.com");
        toolbar.setTitleTextColor(Color.WHITE);
       // toolbar.setSubtitle("官网:EEOB.COM 微信:EX7132");
       // toolbar.setSubtitleTextColor(Color.WHITE);
        //设置menu
        toolbar.inflateMenu(R.menu.menu_item);
        // 设置溢出菜单的图标
       toolbar.setOverflowIcon(toolbar.getResources().getDrawable(R.mipmap.icon_menu));
        // 设置menu item 点击事件
       // resultCallback.OnCreateView(context,toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.toolbar_item_share:
                    //    resultCallback.onShare();
                        break;

                }
                return false;
            }
        });



    }
    public static void OnCreate(Context context , Toolbar toolbar, Component<Toolbar> mvc){
        if(toolbar==null){
            throw new RuntimeException("Stub!");
        }
        mvc.view(context,toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mvc.event(view);
            }
        });



    }
    public interface ToolbarCallback  {
        default void OnCreateView(Context context, Toolbar toolbar, OnClickCallback onClickCallback){
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickCallback.onBack();
                }
            });

        };
    }
    public interface OnClickCallback{
        default void onShare(){};
        default void onBack(){
        };
    }


}
