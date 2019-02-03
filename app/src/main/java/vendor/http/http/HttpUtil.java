package vendor.http.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtil {

    public static void loadRequest(final String url, final HttpCallBack callback) {
        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        String string = (String) msg.obj;
                        callback.loader(string);
                        break;
                    default:
                        break;
                }
            }
        };


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    Response response = null;
                    response = client.newCall(request).execute();//得到Response 对象
                    if (response.isSuccessful()) {
                        String str = (String)response.body().string();
                        Message msg = new Message();
                        msg.obj = str;
                        msg.what = 1;
                        handler.sendMessage(msg);
                    }
                } catch (IOException e) {
                    e.getStackTrace();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        };

        new Thread(runnable).start();
    }


}
