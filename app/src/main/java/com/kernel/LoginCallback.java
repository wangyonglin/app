package com.kernel;

import android.content.Context;
import android.support.annotation.NonNull;

public interface LoginCallback<T> {
    default void login_success(@NonNull Context context,T val){};
    default void login_failure(Exception e){};
}
