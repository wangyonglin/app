package com.khbd.app.support.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class FragmentManager {
    public static void FragmentBuilder(FragmentActivity fragmentActivity, int containerViewId, Fragment fragment){
        FragmentTransaction fragmentTransaction=fragmentActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId,fragment);
        fragmentTransaction.commit();
    }
    public static void SelectFragment(FragmentActivity fragmentActivity, int containerViewId, Fragment fragment){
        FragmentTransaction fragmentTransaction=fragmentActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId,fragment);
        fragmentTransaction.commit();

    }
}
