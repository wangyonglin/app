package com.khbd.app.view;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;

public class AuthDialog  {
    private static MaterialDialog.Builder builder;
    private static MaterialDialog materialDialog;

    public  static void OnCreateView(Context context, ResultCallback resultCallback){
        builder=new MaterialDialog.Builder(context);
        builder.title("Please login...");
        builder.content("not play video ...");
        builder.titleGravity(GravityEnum.CENTER);
        builder.titleColor(Color.parseColor("#000000"));
        builder.autoDismiss(false);
        builder.widgetColor(Color.RED);
        builder.positiveText("login");
        builder.titleGravity(GravityEnum.CENTER);
        builder.buttonsGravity(GravityEnum.START);
        builder.negativeText("cancel");
        materialDialog= builder.build();
        builder.onAny(new MaterialDialog.SingleButtonCallback(){

            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                if (which == DialogAction.POSITIVE) {
                    resultCallback.login();
                    materialDialog.dismiss();
                    Toast.makeText(builder.getContext(), "POSITIVE", Toast.LENGTH_SHORT).show();
                } else if (which == DialogAction.NEGATIVE) {
                    Toast.makeText(builder.getContext(), "NEGATIVE", Toast.LENGTH_SHORT).show();
                    resultCallback.cancel();
                    materialDialog.dismiss();
                }
            }
        });
        materialDialog.show();
    }

    public interface ResultCallback{
        default void login(){}
        default void cancel(){}
    }
}
