package com.vendor.design;

import android.content.Context;
import android.support.design.widget.TabLayout;

import com.vendor.design.tablayout.TabLayoutCategories;

public class CategoriesBuilder {
    public static CategoriesBuilder Create(Context context, TabLayout tabLayout,BuilderView<TabLayout> view){
        view.onCreateView(context,tabLayout);
        view.getView(tabLayout);
        return new CategoriesBuilder();
    }
}
