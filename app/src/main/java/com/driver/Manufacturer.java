package com.driver;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;

import com.heylyn.exception.ResultException;
import com.heylyn.network.ResultCallback;
import com.heylyn.network.download.DownloadUtil;
import com.heylyn.network.download.ProgressCallback;
import com.kernel.BooleanHelper;
import com.khbd.app.R;
import com.khbd.app.view.UpdateDialog;
import com.khbd.app.view.UpdateDialogCallback;

import java.io.File;
import java.io.IOException;

import javakit.network.JavaKitClientResponse;
import javakit.network.JavaKitClientResponseCallback;
import javakit.util.JavaKitJsonUtils;
import vendor.android.log.Logger;

public class Manufacturer {
    private static File file = new File(Environment.getExternalStorageDirectory(),"khbd.apk");
    private static String code = null;
    private static String download = null;
    private static BooleanHelper status = BooleanHelper.retFalse();
    public static Manufacturer check(@NonNull Context context) throws RuntimeException, PackageManager.NameNotFoundException {

        PackageManager manager = context.getPackageManager();
        PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
        JavaKitClientResponse.get(context.getString(R.string.url_app_version), new JavaKitClientResponseCallback<String>() {
            @Override
            public void success(String res) {

                try {
                    code = JavaKitJsonUtils.readTree(res).get("code").asText();
                    download = JavaKitJsonUtils.readTree(res).get("download").asText();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(Integer.parseInt(code)>info.versionCode) {
                    UpdateDialog.build(context,new UpdateDialogCallback(){
                        @Override
                        public void update() {
                       downloader(context,download, new ResultCallback<String>() {
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
                    status.setThat(false);
                }else{
                    status.setThat(true);
                }
            }
        });




    return new Manufacturer();

    }
    public static void downloader(final Context context,String uri,ResultCallback<String> callback){
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            if (!file.exists()) {
                // 判断父文件夹是否存在
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
            }
            DownloadUtil.run(uri, file, new ProgressCallback() {
                @Override
                public void success() {
                    installing(context,file);
                    callback.success("安装成功");
                }

                @Override
                public void loading(int i) {
                    Logger.Info("loading..."+i);
                }

                @Override
                public void failure(ResultException e) {
                    callback.failure(new RuntimeException(e.getMessage()));
                }
            });

        } else {
            callback.failure(new RuntimeException("sd卡未挂载"));
        }

    }
    private static void installing(Context context,File file){
        Logger.Info("进入安装");
        if(!file.exists())
            Logger.Info("文件不存在");
        try {
            //安装应用
            Intent intent = new Intent(Intent.ACTION_VIEW);
            //版本在7.0以上是不能直接通过uri访问的
            // if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            if (Build.VERSION.SDK_INT >= 24) {

                // 由于没有在Activity环境下启动Activity,设置下面的标签
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
                Uri apkUri = FileProvider.getUriForFile(context, "com.khbd.app.fileprovider", file);
                //添加这一句表示对目标应用临时授权该Uri所代表的文件
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
            } else {
                intent.setDataAndType(Uri.fromFile(file),
                        "application/vnd.android.package-archive");
            }

            context.startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }



    }
    public void onErrorResume(OnErrorCallback errorCallback){
        if(status.getThat()){
            errorCallback.ok();
        }
    }
    public interface OnErrorCallback{
        public void ok();
    }
}
