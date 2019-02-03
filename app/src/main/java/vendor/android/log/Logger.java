package vendor.android.log;


import android.util.Log;

public class Logger {
    private static String TAG="KUSHANLOG";
    public static void Debug(String msg){
        Log.d(TAG,msg);
    }
    public static void Info(String msg){
        Log.i(TAG,msg);
    }
    public static void Error(String msg){
        Log.e(TAG,msg);
    }
}
