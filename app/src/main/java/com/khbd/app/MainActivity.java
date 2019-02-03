package com.khbd.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.data.RecyclerData;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.interfaces.AdditionalInterface;

import com.khbd.app.fragment.CategoriesFragment;
import com.khbd.app.fragment.SearchFragment;
import com.util.APIURL;
import com.util.JSONUtil;
import com.util.Logger;
import com.util.ToastUtil;
import com.vendor.design.Atom;

import com.view.FragmentFactory;
import com.view.NavigationFactory;
import com.factory.RecyclerFactory;
import com.view.ToolbarFactory;
import com.wangyonglin.app.network.Video;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javakit.jackson.JacksonUtil;
import javakit.network.HttpClientResponse;
import javakit.network.HttpClientResponse.ResultCallback;

public class MainActivity extends FragmentActivity implements AdditionalInterface,SearchFragment.OnFragmentInteractionListener ,CategoriesFragment.OnFragmentInteractionListener,Runnable{
    private RelativeLayout your_original_layout;
    private BottomNavigationView activity_main_navigation;
    private Toolbar activity_main_toolbar;
    private String url;
    private RecyclerView activity_main_recyclerview;



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
        activity_main_toolbar = (Toolbar)your_original_layout.findViewById(R.id.activity_main_toolbar);
        activity_main_recyclerview=(RecyclerView)your_original_layout.findViewById(R.id.activity_main_recyclerview);
    }



    @Override
    public void initFactory() {
        ToolbarFactory.OnCreate(MainActivity.this, activity_main_toolbar, new ToolbarFactory.ResultCallback() {
            @Override
            public void onShare() {
                ToastUtil.showToast(MainActivity.this,"SearchActivity in onShare...");
            }
        });
        FragmentFactory.OnInit(MainActivity.this,R.id.activity_main_fragment,SearchFragment.newInstance("D","D"));
        RecyclerFactory.OnCreate(MainActivity.this, activity_main_recyclerview, new RecyclerFactory.ResultCallback() {
            @Override
            public void OnCreateView(Context context, RecyclerView recyclerView) {

            }
        });
        NavigationFactory.OnCreate(MainActivity.this, activity_main_navigation, new NavigationFactory.ResultCallback() {
            @Override
            public void onSearch() {
                ToastUtil.showToast(MainActivity.this,"search");
                FragmentFactory.OnReplace(MainActivity.this,R.id.activity_main_fragment,SearchFragment.newInstance("D","DD"));
                updateRecycler(APIURL.ALL(0, 12));
            }

            @Override
            public void onCategories() {
                ToastUtil.showToast(MainActivity.this,"categories");
                FragmentFactory.OnReplace(MainActivity.this,R.id.activity_main_fragment,CategoriesFragment.newInstance("D","DD"));
                updateRecycler(APIURL.CATEGORIES("剧情",0, 12));
            }

            @Override
            public void onMy() {
                ToastUtil.showToast(MainActivity.this,"my");
            }

        });
    }

    @Override
    public void initData() {
        this.updateRecycler(APIURL.ALL(0, 12));
    }



    private void httpClient(String url, javakit.result.ResultCallback<List<Atom>> resultCallback){
        List<Atom> atoms =new ArrayList<Atom>() ;
        HttpClientResponse.String(url, new ResultCallback<String>() {
            @Override
            public void success(String res)  {
                // TODO Auto-generated method stub
                    if(res.isEmpty()){
                        resultCallback.reject(new Exception("http request error empty respones"));
                        return;
                    }
                try {
                    JsonNode result = JacksonUtil.readTree(res).get("result");
                    JSONUtil.Shell shell = new JSONUtil.Shell();
                    shell.setNumberOfElements(result.get("numberOfElements").asInt());
                    shell.setSize(result.get("size").asInt());
                    shell.setTotalElements(result.get("totalElements").asInt());
                    shell.setTotalPages(result.get("totalPages").asInt());
                    JsonNode content =result.get("content");
                    List<Video> list = JacksonUtil.json2list(content,Video.class);
                    for( int i = 0 ; i < list.size() ; i++) {
                        atoms.add( new Atom(list.get(i).getTitle(),list.get(i).getVideo(),list.get(i).getImage(),list.get(i).getUuid()));
                    }
                    resultCallback.resove(atoms);

                } catch (JsonParseException e) {
                    resultCallback.reject(e);
                } catch (JsonMappingException e) {
                    resultCallback.reject(e);
                } catch (IOException e) {
                    resultCallback.reject(e);
                }
            }

            @Override
            public void failure(Exception e) {
                resultCallback.reject(e);
            }
        });
    }


    @Override
    public void onCategoriesInteraction(String str) {
        ToastUtil.showToast(this,str);
        if (str != null) {
            this.updateRecycler(APIURL.CATEGORIES(str,0,12));
        }
    }

    @Override
    public void onSearchFragmentInteraction(String str) {
        ToastUtil.showToast(this,str);
        if (str != null) {
         this.updateRecycler (APIURL.SEARCH(str,0,12));
        }
    }

    public void updateRecycler(String u) {
        url=u;
        new Thread(this).run();
    }

    @Override
    public void run() {
        if (url.isEmpty())
        httpClient(url, new javakit.result.ResultCallback<List<Atom>>() {
            @Override
            public void resove(List<Atom> datas) {
                RecyclerData.load(MainActivity.this, activity_main_recyclerview, datas, new RecyclerData.ResultCallback<Atom>() {
                    @Override
                    public void onItemClick(Atom atom) {
                        ToastUtil.showToast(MainActivity.this,atom.getTitle());
                    }
                });
            }
        });
    }
}
