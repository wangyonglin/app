package vendor.android.support.widget;

import android.content.Context;
import android.os.Build;
import android.print.PrintAttributes;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import vendor.android.DensityUtil;


public class SearchBuilder {

    public static void create(SearchView searchView, ViewGroup.LayoutParams params){

        searchView.setQueryHint("输入您要搜索的电影");
        searchView.setLayoutParams(params);
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);
        searchView.onActionViewExpanded();
        searchView.setBackgroundColor(0x22ff00ff);
        searchView.setIconifiedByDefault(true);
    }
    public static void create(SearchView searchView){
        searchView.setQueryHint("输入您要搜索的电影");
        searchView.setIconified(true);
        searchView.setFocusable(false);
        searchView.setIconifiedByDefault(true);
        //searchView.setSubmitButtonEnabled(true);
        searchView.onActionViewExpanded();
        searchView.setBackgroundColor(0x22ff00ff);
        searchView.setIconifiedByDefault(true);
        searchView.clearFocus();
    }

    public static void create(SearchView searchView,RelativeLayout.LayoutParams layoutParams,SearchView.OnQueryTextListener listener){
        searchView.setLayoutParams(layoutParams);
        searchView.setQueryHint("输入您要搜索的电影");
        searchView.setIconified(true);
        searchView.setFocusable(false);
        searchView.setIconifiedByDefault(true);
        //searchView.setSubmitButtonEnabled(true);
        searchView.onActionViewExpanded();
        //searchView.setBackgroundColor(0x22ff00ff);
        searchView.setIconifiedByDefault(true);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(listener);
    }
}
