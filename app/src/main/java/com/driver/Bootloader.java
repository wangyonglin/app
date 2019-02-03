package com.driver;

import android.app.Application;

import com.baidu.mobstat.StatService;



public class Bootloader extends Application {
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
    }
}
