package com.kernel;

import android.content.Context;
import android.support.annotation.NonNull;

import com.zyao89.view.zloading.ZLoadingDialog;

public class Loading extends ZLoadingDialog {
    public Loading(@NonNull Context context) {
        super(context);
    }

    public Loading(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }
}
