package com.khbd.app.view;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.khbd.app.R;

import java.util.Timer;
import java.util.TimerTask;

public class MessageBox {
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void run(@NonNull Context context, String msg, int duration){
        Toast toast=new Toast(context);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0, 0);//设置对齐方式
        LinearLayout layout=new LinearLayout(context);
        layout.setBackgroundColor(context.getColor(R.color.primary));
        layout.setPadding(10,10,10,10);
        TextView tv=new TextView(context);
        tv.setTextColor(context.getColor(R.color.icons));
        tv.setText(msg);
        tv.setTextSize(23);
        layout.addView(tv);

        toast.setView(layout);
        show(toast,duration);

    }

    public static void show(final Toast toast, final int cnt) {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        }, 0, 3000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, cnt );
    }

}
