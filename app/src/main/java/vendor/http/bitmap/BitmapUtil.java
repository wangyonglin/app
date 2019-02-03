package vendor.http.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BitmapUtil {

    public static void loadBitmap(final String url, final BitmapCallBack callback) {
        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        Bitmap bitmap = (Bitmap) msg.obj;
                        callback.loadBitmap(bitmap);
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
                        Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                        Message msg = new Message();
                        msg.obj = bitmap;
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
