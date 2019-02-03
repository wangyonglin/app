package com.vendor.design;

import android.content.Context;
import android.view.ViewGroup.LayoutParams;

public interface BuilderView<T> {
     public T onCreateView(Context context,T view);
     public void getView(T view);
}
