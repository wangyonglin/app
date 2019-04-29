package com.kernel;

import android.app.Application;

import com.driver.Bootloader;

public interface BootloaderParamsCallback<T extends Application> {
        default void token(T bootloader,String user, String token)throws Exception{}
        default void login(){}
        default void failure(Exception e){};
    }

