package com.interfaces;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface AdditionalInterface {
    default void initViews(){};
    default void initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){};
    default void initFactory(){}
    default void initEvent(){}
    default void initData(){}
    default void initData(Bundle savedInstanceState){}
}
