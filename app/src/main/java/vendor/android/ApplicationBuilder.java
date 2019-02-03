package vendor.android;


import android.app.Application;
import android.util.Log;

import com.baidu.mobstat.StatService;
import com.google.android.gms.ads.MobileAds;



public class ApplicationBuilder extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
      StatService.start(this);
        // 打开调试开关，可以查看logcat日志。版本发布前，为避免影响性能，移除此代码
        // 查看方法：adb logcat -s sdkstat
        StatService.setDebugOn(true);

        // 开启自动埋点统计，为保证所有页面都能准确统计，建议在Application中调用。
        // 第三个参数：autoTrackWebview：
        // 如果设置为true，则自动track所有webview；如果设置为false，则不自动track webview，
        // 如需对webview进行统计，需要对特定webview调用trackWebView() 即可。
        // 重要：如果有对webview设置过webchromeclient，则需要调用trackWebView() 接口将WebChromeClient对象传入，
        // 否则开发者自定义的回调无法收到。
        StatService.autoTrace(this, true, false);

        // 根据需求使用
        // StatService.autoTrace(this, true, false);



    }

}
