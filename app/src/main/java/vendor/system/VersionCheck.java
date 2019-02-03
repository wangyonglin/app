package vendor.system;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;


import com.heylyn.network.ResultCallback;

import vendor.android.log.Logger;
import vendor.network.ReleaseSkullGenie;
import vendor.setup.Install;

import vendor.setup.UpdateView;


public class VersionCheck {
    public static void run(Context context,OnResultCallback callback){
        ReleaseSkullGenie.ParseRelease("https://api.eeob.com/app/release", new ReleaseSkullGenie.VersionCallback() {
            @Override
            public void call(String title, String pubdate, String websites, String content, String name, String code, String url) {
                int versionCode=  Integer.parseInt(code);
                try {
                    PackageManager manager = context.getPackageManager();
                    PackageInfo info = manager.getPackageInfo(context.getPackageName(),
                            0);

                        if(versionCode>info.versionCode){

                            new UpdateView.Show(context,title,content).start(new UpdateView.OnClickCallback() {
                                @Override
                                public void update() {
                                    Logger.Info("现在为你下载app...");
                                    Install.downloader(context, url, new ResultCallback<String>() {
                                        @Override
                                        public void success(String s) {
                                            Logger.Info(s);
                                        }

                                        @Override
                                        public void failure(RuntimeException e) {
                                            Logger.Error(e.getMessage());
                                        }
                                    });
                                }

                                @Override
                                public void cancel() {

                                }
                            });
                        }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        });



    }
    public interface OnResultCallback{
        void success();
        void failure();
    }
}
