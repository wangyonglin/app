package com.vendor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

public class ImageLoader {
    public String image_path;

    public  ImageLoader(String image_path) {
            this.image_path = image_path;
        }

    //加载图片
    public Bitmap getURLimage(String url) {
        Bitmap bmp = null;
        try {
            URL myurl = new URL(url);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.setConnectTimeout(6000);//设置超时
            conn.setDoInput(true);
            conn.setUseCaches(false);//不缓存
            conn.connect();
            InputStream is = conn.getInputStream();//获得图片的数据流
            bmp = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }

        public void loadImage(final ImageCallBack callBack) {

            final Handler handler = new Handler() {

                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    Bitmap bitmap = (Bitmap) msg.obj;
                    callBack.getBitmap(bitmap);
                }

            };

            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        /*
                        Drawable drawable = Drawable.createFromStream(new URL(
                                image_path).openStream(), "");
                        System.out.println(Thread.currentThread().getName() + "线程结束。"+drawable.toString());
                        */
                        Bitmap bitmap = getURLimage(image_path);
                        Message message = Message.obtain();
                        message.obj = bitmap;
                        handler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }


}

