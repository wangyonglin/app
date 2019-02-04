package com.factory;

import android.content.Context;
import android.support.v7.widget.SearchView;

public class SearchFactory {

    public static SearchFactory OnCreate(Context context, SearchView searchView,ResultCallback callback){
        callback.onCreateView(context,searchView);
        /*
        MetricsBuilder.create(context, new MetricsBuilder.DisplayMetricsCallback() {
            @Override
            public void metrics(float density, int widthPixels, int heightPixels, float xdpi, float ydpi) {
                RelativeLayout.LayoutParams params  = new  RelativeLayout.LayoutParams(
                        widthPixels/100*98,
                        DensityUtil.dip2px(45,context));
                int h= DensityUtil.dip2px(60,context);
                params.setMargins(0,45+h,0,45);
                params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                searchView.setLayoutParams(params);
            }
        });
        */
        searchView.setQueryHint("请输入您要搜索的电影");
        // searchView.setIconifiedByDefault(false);
        searchView.setIconified(false);
        searchView.clearFocus();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                callback.onQueryTextSubmit(context,searchView,s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return new SearchFactory();
    }

    public interface ResultCallback{
        default void onCreateView(Context context, SearchView searchView){};
        default void onQueryTextSubmit(Context context,SearchView searchView,String search){};
    }

}