package com.khbd.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.data.RecyclerData;
import com.factory.LoadingFactory;

import com.factory.ScrollingTextFactory;
import com.interfaces.AdditionalInterface;

import com.kernel.Loading;
import com.khbd.app.fragment.CategoriesFragment;
import com.khbd.app.fragment.MeFragment;
import com.khbd.app.fragment.SearchFragment;

import com.khbd.data.httpClintHelper;
import com.util.APIURL;

import com.util.ToastUtil;
import com.vendor.design.Atom;

import com.factory.FragmentFactory;
import com.factory.NavigationFactory;
import com.factory.RecyclerFactory;
import com.factory.ToolbarFactory;
import java.util.List;

public class MainActivity extends FragmentActivity implements AdditionalInterface,
        SearchFragment.OnFragmentInteractionListener ,
        CategoriesFragment.OnFragmentInteractionListener,
        MeFragment.OnFragmentInteractionListener{
    private RelativeLayout your_original_layout;
    private BottomNavigationView activity_main_navigation;

    private String url;
    private RecyclerView activity_main_recyclerview;
    private Loading loading;


   @SuppressLint("NewApi")  @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       your_original_layout =(RelativeLayout)LayoutInflater.from(this).inflate(R.layout.activity_main,null);
       setContentView(your_original_layout);
       StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
       StrictMode.setThreadPolicy(policy);

        this.initViews();
        this.initFactory();
        this.initData();
    }



    @Override
    public void initViews() {
        activity_main_navigation = (BottomNavigationView) your_original_layout.findViewById(R.id.activity_main_navigation);
     //   activity_main_recyclerview=(RecyclerView)your_original_layout.findViewById(R.id.activity_main_recyclerview);
    }



    @Override
    public void initFactory() {
       //loading =LoadingFactory.OnCreate(MainActivity.this);
     //  loading.show();
        FragmentFactory.OnInit(MainActivity.this,R.id.activity_main_fragment,SearchFragment.newInstance("D","D"));

        NavigationFactory.OnCreate(MainActivity.this, activity_main_navigation, new NavigationFactory.ResultCallback() {

            @Override
            public void onDiscover() {
                ToastUtil.showToast(MainActivity.this,"search");
                FragmentFactory.OnReplace(MainActivity.this,R.id.activity_main_fragment,SearchFragment.newInstance("D","DD"));
            }

            @Override
            public void onCategories() {
                ToastUtil.showToast(MainActivity.this,"categories");
                FragmentFactory.OnReplace(MainActivity.this,R.id.activity_main_fragment,CategoriesFragment.newInstance("D","DD"));
            }

            @Override
            public void onPornstars() {
                ToastUtil.showToast(MainActivity.this,"search");
                FragmentFactory.OnReplace(MainActivity.this,R.id.activity_main_fragment,SearchFragment.newInstance("D","DD"));
            }

            @Override
            public void onMy() {
                ToastUtil.showToast(MainActivity.this,"my");
                FragmentFactory.OnReplace(MainActivity.this,R.id.activity_main_fragment,MeFragment.newInstance("D","DD"));
            }

        });
    }

    @Override
    public void initData() {

    }






    @Override
    public void onCategoriesInteraction(String str) {
        ToastUtil.showToast(this,str);
      //  if (str != null) {
         //   this.updateRecycler(APIURL.CATEGORIES(str,0,12));
     //   }
    }

    @Override
    public void onSearchFragmentInteraction(String str) {
       // ToastUtil.showToast(this,str);
        //if (str != null) {
       //     this.updateRecycler(APIURL.SEARCH(str,0,12));
      //  }
    }


    @Override
    public void onMeFragmentInteraction(String str) {

    }
}
