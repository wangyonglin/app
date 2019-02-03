package vendor.setup;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;

import com.heylyn.exception.ResultException;
import com.heylyn.network.ResultCallback;
import com.heylyn.network.download.DownloadUtil;
import com.heylyn.network.download.ProgressCallback;

import java.io.File;

import io.vov.vitamio.utils.Log;
import vendor.android.log.Logger;


public   class Install {
    private static File file = new File(Environment.getExternalStorageDirectory(),"khbd.apk");
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
}
