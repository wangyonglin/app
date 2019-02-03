package com.vendor.design;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

public interface Builders<T> {
    public T onCreateView(Context context, T x);
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l);
}