package com.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import com.khbd.app.R;
import vendor.android.RouteUtil;

public class NavigationFactory  {
    public static void OnCreate(Context context, BottomNavigationView bottomNavigationView, ResultCallback resultCallback){
        if(bottomNavigationView == null ){
            throw new RuntimeException("Stub!");
        }
            resultCallback.OnCreateView(context,bottomNavigationView);
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_search:
                            resultCallback.onSearch();
                            return true;
                        case R.id.navigation_movie:
                            resultCallback.onCategories();
                            return true;
                        case R.id.navigation_notifications:
                            resultCallback.onMy();
                            return true;
                    }
                    return false;
                }
            });
    }
    public interface ResultCallback{
        default void OnCreateView(Context context,BottomNavigationView view){};
        default void onSearch(){};
        default void onCategories(){};
        default void onMy(){};
    }
}
