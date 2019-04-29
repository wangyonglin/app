package com.kernel.component;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.khbd.app.R;

public class MessageBox {
    public  static int  INFO=R.mipmap.ic_notice;
    public int Alert=0;
    private static AlertDialog dialog;
    private static   AlertDialog.Builder builder;
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static MessageBox make(Context context, String title, String msg,int MessageBox){
        builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setIcon(MessageBox);
        dialog=builder.create();
        LinearLayout layout=new LinearLayout(context);
        // layout.setBackgroundColor(context.getColor(R.color.primary));
        layout.setPadding(50,50,50,50);
        TextView tv=new TextView(context);
        tv.setTextColor(context.getColor(R.color.black));
        tv.setText(Html.fromHtml(msg));
        tv.setTextSize(18);
        layout.addView(tv);
        dialog.setView(layout);
        return new MessageBox();
    }
    public void show(){
        dialog.show();

    }
}
