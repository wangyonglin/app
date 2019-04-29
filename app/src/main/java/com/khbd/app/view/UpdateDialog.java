package com.khbd.app.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;


public class UpdateDialog {

        private static MaterialDialog.Builder builder;
        private static MaterialDialog materialDialog;
        public  static void build(Context context,UpdateDialogCallback callback){
            builder=new MaterialDialog.Builder(context);
            builder.title("检测到新版本");
            builder.content("请及时更新");
            builder.titleGravity(GravityEnum.CENTER);
            builder.titleColor(Color.parseColor("#000000"));
            builder.autoDismiss(false);
            builder.widgetColor(Color.RED);
            builder.positiveText("更新");
            builder.titleGravity(GravityEnum.CENTER);
            builder.buttonsGravity(GravityEnum.START);
            builder.negativeText("取消");
            materialDialog= builder.build();
            builder.onAny(new MaterialDialog.SingleButtonCallback(){

                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    if (which == DialogAction.POSITIVE) {
                        callback.update();
                        materialDialog.dismiss();

                    } else if (which == DialogAction.NEGATIVE) {
                        callback.cancel();
                        materialDialog.dismiss();
                    }
                }
            });
            materialDialog.show();
        }


    }
