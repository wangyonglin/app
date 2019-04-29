package com.driver.receiver.network;

import android.content.Context;
import android.content.IntentFilter;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class NetworkReceiver {
    public interface NetworkChangeListener{
        //@Subscribe(threadMode = ThreadMode.MAIN)
        void onNetwork(NetworkChangeEvent event);
    }
    private static NetworkConnectChangedReceiver mNetworkConnectChangedReceiver;
    public static void register(Context context){
        EventBus.getDefault().register(context);
        mNetworkConnectChangedReceiver = new NetworkConnectChangedReceiver();
        context.registerReceiver(mNetworkConnectChangedReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public static void unregister(Context context){
        EventBus.getDefault().unregister(context);
        context.unregisterReceiver(mNetworkConnectChangedReceiver);
    }
}
