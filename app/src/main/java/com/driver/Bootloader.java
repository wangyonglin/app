package com.driver;

import android.app.Application;
import android.util.Log;

import com.baidu.mobstat.StatService;
import com.flurry.android.FlurryAgent;


public class Bootloader extends Application {
    private static String FLURRY_API_KEY = "42WF7PJQ4JZBRNN4YW82";
    @Override
    public void onCreate() {
        super.onCreate();

        //百度统计Start
        StatService.start(this);
        StatService.autoTrace(this);
        StatService.autoTrace(this,true, false);
        //百度统计End
        // 获取测试设备ID
        String testDeviceId = StatService.getTestDeviceId(this);
        // 日志输出
        android.util.Log.d("BaiduMobStat", "Test DeviceId : " + testDeviceId);
        //Start Flurry
        new FlurryAgent.Builder()
                .withLogEnabled(true)
                .withCaptureUncaughtExceptions(true)
                .withContinueSessionMillis(10000)
                .withLogLevel(Log.VERBOSE)
                .build(this, FLURRY_API_KEY);
        //End Flurry
    }
}
