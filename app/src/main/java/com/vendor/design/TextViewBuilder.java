package com.vendor.design;

import android.content.Context;
import android.widget.TextView;

public class TextViewBuilder {
    public static TextViewBuilder onCreateView(Context context, TextView view, BuilderCallback callback){
        callback.onCreate(context,view);
        return new TextViewBuilder();
    }
    public interface BuilderCallback{
        public void onCreate(Context context,TextView view);
    }
}
