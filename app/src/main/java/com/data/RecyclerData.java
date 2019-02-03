package com.data;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.khbd.movie.RecyclerAdapter;

import com.vendor.design.Atom;
import com.vendor.event.OnMovieItemClickListener;

import java.util.List;

public class RecyclerData {
    public static  void  load(Context context, RecyclerView recyclerView, List<Atom> list, ResultCallback<Atom> resultCallback){
        int spanCount = 3;
        final GridLayoutManager layoutManager = new GridLayoutManager(context,spanCount);//定义瀑布流管理器，第一个参数是列数，第二个是方向。
        //layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);//不设置的话，图片闪烁错位，有可能有整列错位的情况。
        recyclerView.setPadding(0, 0, 0, 0);
        recyclerView.setLayoutManager(layoutManager);//设置瀑布流管理器
        RecyclerAdapter adapter = new RecyclerAdapter(list,context);
        adapter.setItemClickListener(new OnMovieItemClickListener() {
            @Override
            public void onItemClick(Atom atom) {
                resultCallback.onItemClick(atom);
            }
        });
        recyclerView.setAdapter(adapter);
    }
    public interface ResultCallback<T>{
        default void onItemClick(T cls){};
    }
}
