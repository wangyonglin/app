package com.factory;

import android.content.Context;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.khbd.data.Webcams;
import com.khbd.movie.RecyclerAdapter;
import com.vendor.design.Atom;
import com.vendor.event.OnMovieItemClickListener;

import java.util.List;


public class RecyclerViewManager {
    public static RecyclerViewManager init(Context context, RecyclerView recyclerView, List<Webcams> list, ResultCallback<Webcams> callback){

        int spanCount = 3;
        final GridLayoutManager layoutManager = new GridLayoutManager(context,spanCount);//定义瀑布流管理器，第一个参数是列数，第二个是方向。
        //layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);//不设置的话，图片闪烁错位，有可能有整列错位的情况。
        recyclerView.setPadding(0, 0, 0, 0);
        recyclerView.setLayoutManager(layoutManager);//设置瀑布流管理器
        RecyclerAdapter adapter = new RecyclerAdapter(list,context);
        adapter.setItemClickListener(new OnMovieItemClickListener() {
            @Override
            public void onItemClick(Webcams webcams) {
                callback.onItemClick(webcams);
            }
        });
        recyclerView.setAdapter(adapter);//设置适配器
        callback.pop(context,recyclerView,list);
        return new RecyclerViewManager();
    }
    public interface ResultCallback<T>{
        default void pop(Context context,RecyclerView view,List<T> ts){};
        default void onItemClick(T cls){};
    }
}
