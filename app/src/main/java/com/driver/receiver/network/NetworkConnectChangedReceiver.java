package com.driver.receiver.network;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.greenrobot.eventbus.EventBus;
public class NetworkConnectChangedReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //**判断当前的网络连接状态是否可用*/
            boolean isConnected = NetUtils.isConnected(context);
            EventBus.getDefault().post(new NetworkChangeEvent(isConnected));
        }
}

