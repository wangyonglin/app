package com.khbd.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import javakit.network.HttpClientResponse;

public class BitmapTask extends AsyncTask<Object, Void, Bitmap> {
    private Bitmap bitmap;
    private ImageView ivPic;
    private String url;

    /**
     * 后台耗时操作,存在于子线程中
     *
     * @param params
     * @return
     */
    @Override
    protected Bitmap doInBackground(Object[] params) {
        ivPic = (ImageView) params[0];//在网上加载完图片之后，更新的ImageView
        url = (String) params[1];//网络图片的URL地址
        return downLoadBitmap(url);
    }
    /**
     * 耗时方法结束后执行该方法,主线程中
     *
     * @param result
     */
    @Override
    protected void onPostExecute(Bitmap result) {
        if (result != null) {
            ivPic.setImageBitmap(result);//更新UI
        }
    }

    /**
     * 网络下载图片
     * @param url
     * @return
     */
    private Bitmap downLoadBitmap(String url) {

        HttpClientResponse.InputStream(url, new HttpClientResponse.ResultCallback<InputStream>() {
            @Override
            public void success(InputStream res) throws IOException {
                /**图片压缩
                 BitmapFactory.Options options = new BitmapFactory.Options();
                 options.inSampleSize=2;//宽高压缩为原来的1/2
                 options.inPreferredConfig=Bitmap.Config.ARGB_4444;
                 bitmap = BitmapFactory.decodeStream(conn.getInputStream(),null,options);
                 */
                /*

                     Bitmap bitmap = BitmapFactory.decodeStream(res);
                ViewGroup.LayoutParams imageLayoutParams = viewHolder.moviePoster.getLayoutParams();
                Resources resources = mContext.getResources();
                DisplayMetrics dm = resources.getDisplayMetrics();
                int width = (dm.widthPixels- DensityUtil.dip2px(12,mContext))/3;
                imageLayoutParams.width = width;//获取实际展示的图片宽度
                imageLayoutParams.height = bitmap.getHeight();//获取最终图片高度

                 */
                bitmap=BitmapFactory.decodeStream(res);
            }
        });
       return bitmap;
    }
}