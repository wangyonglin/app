package com.driver.receiver.network;


import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.khbd.app.R;

public class NetworkDialog {
    private static   AlertDialog.Builder builder;
    public static NetworkDialog make(Context context){
        builder = new AlertDialog.Builder(context);
        builder.setIcon(R.mipmap.network_fail_red);
        builder.setTitle("Network Status");
        builder.create();
        builder.setMessage("Network connection unavailable");
        return new NetworkDialog();
    }
    public void show(){
        builder.show();
    }

}
