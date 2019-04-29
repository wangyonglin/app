package com.driver.receiver.network;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.kernel.MessageWrap;

import org.greenrobot.eventbus.EventBus;


public class NetworkConnectChangedReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //**判断当前的网络连接状态是否可用*/
            /*
            Log.i("@###","NetworkConnectChangedReceiver");
            boolean isConnected = NetUtils.isConnected(context);
            if(isConnected){
                EventBus.getDefault().post(MessageWrap.getInstance("network yes"));
            }else {
                EventBus.getDefault().post(MessageWrap.getInstance("network no"));
            }
        */
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            if(networkInfo!=null && networkInfo.getType()== ConnectivityManager.TYPE_WIFI){
                Toast.makeText(context,"now is wifi",Toast.LENGTH_SHORT).show();

            }
            else if(networkInfo!=null && networkInfo.getType()== ConnectivityManager.TYPE_MOBILE){
                Toast.makeText(context,"now is 移动数据",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context,"没有网络",Toast.LENGTH_SHORT).show();
            }
        }
}

