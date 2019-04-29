package com.khbd.app;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.driver.Manufacturer;

import com.driver.receiver.network.NetworkChangeEvent;
import com.driver.receiver.network.NetworkDialog;
import com.driver.receiver.network.NetworkReceiver;

import com.interfaces.AdditionalInterface;
import com.khbd.app.fragment.CategoriesFragment;
import com.khbd.app.fragment.MainFragment;
import com.khbd.app.fragment.UserFragment;
import com.factory.FragmentFactory;
import com.factory.NavigationFactory;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends FragmentActivity implements AdditionalInterface,
        NetworkReceiver.NetworkChangeListener,
        MainFragment.OnFragmentInteractionListener ,
        CategoriesFragment.OnFragmentInteractionListener,
        UserFragment.OnFragmentInteractionListener{
    private static String TAG="MainActivity";
    private RelativeLayout your_original_layout;
    private BottomNavigationView activity_main_navigation;


    private ConnectivityManager.NetworkCallback networkCallback;
    private ConnectivityManager connectivityManager;

    @Override
    protected void onResume() {
        super.onResume();

    }

    @SuppressLint("NewApi")  @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       your_original_layout =(RelativeLayout)LayoutInflater.from(this).inflate(R.layout.activity_main,null);

       setContentView(your_original_layout);
       StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
       StrictMode.setThreadPolicy(policy);
       try {
           Manufacturer.check(this);
       } catch (PackageManager.NameNotFoundException e) {
           Log.i(TAG,e.getMessage());
           e.printStackTrace();
           Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
       }
       this.initViews();
        this.initFactory();
        this.initData();
        NetworkReceiver.register(this);

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
        FragmentFactory.OnInit(MainActivity.this,R.id.activity_main_fragment,MainFragment.newInstance("D","D"));

        NavigationFactory.OnCreate(MainActivity.this, activity_main_navigation, new NavigationFactory.ResultCallback() {

            @Override
            public void onDiscover() {
                FragmentFactory.OnReplace(MainActivity.this,R.id.activity_main_fragment,MainFragment.newInstance("D","DD"));
            }

            @Override
            public void onCategories() {

                FragmentFactory.OnReplace(MainActivity.this,R.id.activity_main_fragment,CategoriesFragment.newInstance("D","DD"));
            }


            @Override
            public void onMy() {

                FragmentFactory.OnReplace(MainActivity.this,R.id.activity_main_fragment,UserFragment.newInstance("D","DD"));
            }

        });
    }

    @Override
    public void initData() {

    }






    @Override
    public void onCategoriesInteraction(String str) {

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
    @Override
    public void finish() {
        super.finish();

    }


    @Override
    protected void onDestroy() {
       NetworkReceiver.unregister(this);
       super.onDestroy();
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNetwork(NetworkChangeEvent event) {
        if(!event.isConnected){
            NetworkDialog.make(this).show();
        }
    }
}
