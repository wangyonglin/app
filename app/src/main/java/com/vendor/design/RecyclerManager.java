package com.vendor.design;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.khbd.data.Webcams;
import com.khbd.movie.RecyclerAdapter;
import com.vendor.event.OnMovieItemClickListener;

import java.util.List;

import vendor.android.RouteUtil;

public class RecyclerManager {
    public static void Show(final Context context, RecyclerView recyclerView, List<Webcams> data, int spanCount){
        final GridLayoutManager layoutManager = new GridLayoutManager(context,spanCount);//定义瀑布流管理器，第一个参数是列数，第二个是方向。
        //layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);//不设置的话，图片闪烁错位，有可能有整列错位的情况。
        recyclerView.setPadding(0, 0, 0, 0);
        recyclerView.setLayoutManager(layoutManager);//设置瀑布流管理器
        RecyclerAdapter adapter = new RecyclerAdapter(data,context);
        adapter.setItemClickListener(new OnMovieItemClickListener() {
            @Override
            public void onItemClick(Webcams webcams) {
                Toast.makeText(context,webcams.getTitle().toString(), Toast.LENGTH_SHORT).show();

            }
        });
        recyclerView.setAdapter(adapter);//设置适配器
    }
}
