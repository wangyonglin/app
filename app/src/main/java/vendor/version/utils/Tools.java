package vendor.version.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;


import com.heylyn.network.ResultCallback;




/**
 * Created by huagnshuyuan on 2017/3/16.
 */
public class Tools {


    public static class Version{
        private static String VersionName = "";
        private static int VersionCode = 1;
        private static int currentVersion(Context context){
            try {
                PackageManager manager = context.getPackageManager();
                PackageInfo info = manager.getPackageInfo(context.getPackageName(),
                        0);
                VersionName=info.versionName;
                VersionCode=info.versionCode;


            } catch (Exception e) {
                e.printStackTrace();
            }
            return VersionCode;
        }
        private static int officialVersion(String url){
            return 2;
        }
        public static void run(Context context,String url,ResultCallback<String> callback){
         if(officialVersion(url)>currentVersion(context)){
            callback.success("联.要为你做盘红烧肉");
         }

        }
    }
}
