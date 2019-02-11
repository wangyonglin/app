package com.khbd.app;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;


import com.interfaces.AdditionalInterface;
import com.wangyonglin.app.domain.ItemClass;
import com.wangyonglin.design.widget.ItemClassAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vendor.android.ActivityBuilder;


public class DetailActivity extends ActivityBuilder implements AdditionalInterface {
    private ListView listView;
    private List<ItemClass> datas= new ArrayList<ItemClass>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout your_original_layout =(RelativeLayout) LayoutInflater.from(this).inflate(R.layout.activity_detail,null);
        setContentView(your_original_layout);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initFactory() {

    }

    @Override
    public void initData() {

    }
}


