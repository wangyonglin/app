package com.factory;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import com.khbd.app.R;
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
                        case R.id.navigation_discover:
                            resultCallback.onDiscover();
                            return true;
                        case R.id.navigation_categories:
                            resultCallback.onCategories();
                            return true;
                        case R.id.navigation_pornstars:
                            resultCallback.onPornstars();
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
        default void onDiscover(){};
        default void onCategories(){};
        default void onPornstars(){};
        default void onMy(){};
    }
}
