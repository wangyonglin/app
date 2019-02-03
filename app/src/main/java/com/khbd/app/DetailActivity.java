package com.khbd.app;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;



import com.wangyonglin.app.domain.ItemClass;
import com.wangyonglin.design.widget.ItemClassAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vendor.android.ActivityBuilder;


public class DetailActivity extends ActivityBuilder {
    private ListView listView;
    private List<ItemClass> datas= new ArrayList<ItemClass>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout your_original_layout =(RelativeLayout) LayoutInflater.from(this).inflate(R.layout.activity_detail,null);

        setContentView(your_original_layout);
    }


}


