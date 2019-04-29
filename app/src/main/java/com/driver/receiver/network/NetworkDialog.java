package com.driver.receiver.network;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.khbd.app.R;

public class NetworkDialog {
    private static View mTipView;
    private static WindowManager mWindowManager;
    private static  WindowManager.LayoutParams mLayoutParams;

    public static void OnCreateView(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("网络提示信息");
        builder.setMessage("网络不可用，如果继续，请先设置网络！");
        builder.create();
        builder.show();
    }

    private static void hasNetWork(boolean isCheckNetWork, boolean has) {
        if (isCheckNetWork) {
            if (has) {
                if (mTipView != null && mTipView.getParent() != null) {
                    mWindowManager.removeView(mTipView);
                }
            } else {
                if (mTipView.getParent() == null) {
                    mWindowManager.addView(mTipView, mLayoutParams);
                }
            }
        }
    }
}
