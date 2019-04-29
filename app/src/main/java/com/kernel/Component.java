package com.kernel;

import android.content.Context;
import android.view.View;

public abstract class Component<T extends View> {
    public void view(Context context,T view){}
    public void event(View view){}
    public void data(T v){}
}
