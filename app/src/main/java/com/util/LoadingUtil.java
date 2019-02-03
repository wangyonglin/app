package com.util;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

public class LoadingUtil implements MaterialDialog.SingleButtonCallback{
    private static MaterialDialog.Builder builder;
    private static MaterialDialog materialDialog;
    private static Context mContext;

    public static LoadingUtil Create(Context context){
        mContext=context;
        builder = new  MaterialDialog.Builder(context);
        builder.title("检测到新版本");
        builder.content("Budddddddddddddddddddddddddddddddddddddddddddddddd");
        builder.negativeText("以后再说");
        materialDialog=builder.build();
        return new LoadingUtil();
    }
    public void show(){
        builder.onAny(LoadingUtil.this);
        materialDialog.show();
    }
    public  void hide(){
        materialDialog.hide();
    }

    @Override
    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
        if (which == DialogAction.POSITIVE) {
            materialDialog.dismiss();

        } else if (which == DialogAction.NEGATIVE) {
            Toast.makeText(mContext, "请升级哦，有新功能哦！", Toast.LENGTH_LONG).show();
            materialDialog.dismiss();
        }
    }
}
