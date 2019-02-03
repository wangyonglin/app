package vendor.android;

import android.app.Activity;
import android.view.WindowManager;

public class AndroidUtil {
    public static void FullScreen(Activity context){
        // 设置全屏
        context.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }



}
