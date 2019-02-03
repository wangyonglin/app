package com.vendor.design;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListViewBuilder {
    public static ListViewBuilder create(Context context, ListView listView, Builders<ListView> builders) {
        ListView view = builders.onCreateView(context,listView);
       // view.setOnItemClickListener(new AdapterView.OnItemClickListener(){});
        return new ListViewBuilder();
    }
}


