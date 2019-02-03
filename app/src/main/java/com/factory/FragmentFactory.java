package com.view;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class FragmentFactory  {
    public static void OnInit(FragmentActivity fragmentActivity, @IdRes int containerViewId, @NonNull Fragment fragment) {
        FragmentTransaction fragmentTransaction=fragmentActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId,fragment);
        fragmentTransaction.commit();
    }
    public static void OnReplace(FragmentActivity fragmentActivity, @IdRes int containerViewId, @NonNull Fragment fragment) {
        FragmentTransaction fragmentTransaction=fragmentActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId,fragment);
        fragmentTransaction.commit();
    }
}
