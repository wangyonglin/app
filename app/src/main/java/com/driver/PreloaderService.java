package com.driver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class PreloaderService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    /**
     * 开始预加载进程
     */
    private void startHideService(){
        Intent intent = new Intent(this, PreloaderService.class);
        this.startService(intent);
    }

    private void stopHideService(){
        Intent intent = new Intent(this, PreloaderService.class);
        this.stopService(intent);
    }
}
