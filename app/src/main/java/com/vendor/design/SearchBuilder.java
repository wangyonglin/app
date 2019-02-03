package com.vendor.design;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.SearchView;
import android.widget.RelativeLayout;

import com.vendor.design.tablayout.TabLayoutCategories;

import vendor.android.DensityUtil;
import vendor.android.framework.FrameworkUtil;

public class SearchBuilder {

    public static SearchBuilder create(Context context, SearchView searchView,BuilderCallback callback){
        MetricsBuilder.create(context, new MetricsBuilder.DisplayMetricsCallback() {
            @Override
            public void metrics(float density, int widthPixels, int heightPixels, float xdpi, float ydpi) {
                RelativeLayout.LayoutParams params  = new  RelativeLayout.LayoutParams(
                        widthPixels/100*98,
                        DensityUtil.dip2px(45,context));
                params.setMargins(0,45,0,45);
                params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                searchView.setLayoutParams(params);
            }
        });
        searchView.setQueryHint("请输入您要搜索的电影");
        // searchView.setIconifiedByDefault(false);
        searchView.setIconified(false);
        searchView.clearFocus();

        callback.onCreateView(context,searchView);
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
        return new SearchBuilder();
    }

    public interface BuilderCallback{
        default void onCreateView(Context context, SearchView searchView){};
        default void onQueryTextSubmit(Context context,SearchView searchView,String search){};
    }

}
