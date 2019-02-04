package com.factory;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import com.kernel.Loading;
import static com.zyao89.view.zloading.Z_TYPE.DOUBLE_CIRCLE;

public class LoadingFactory {
    public static Loading OnCreate(@NonNull Context context) {
        Loading loading = new Loading(context);
        loading.setLoadingBuilder(DOUBLE_CIRCLE)//设置类型
                .setLoadingColor(Color.parseColor("#FF4081"))//颜色
                .setHintText("Loading...")
                .setHintTextSize(16) // 设置字体大小 dp
                .setHintTextColor(Color.parseColor("#FF4081"))  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                .setDialogBackgroundColor(Color.WHITE); // 设置背景色，默认白色
        loading.setCanceledOnTouchOutside(false);
        loading.setCancelable(false);
        return loading;
    }
}
