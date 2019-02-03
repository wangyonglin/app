package com.vendor.design;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import com.heylyn.network.HttpClient;
import com.heylyn.network.ResultCallback;

import java.io.InputStream;

public class ImageViewBuilder {

    public static ImageViewBuilder onCreateView(Activity activity, ImageView imageView,BuilderCallback callback){

        HttpClient.urlResultInputStream(callback.onCreate(imageView),new ResultCallback<InputStream>(){

            @Override
            public void success(InputStream inputStream) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void failure(RuntimeException e) {

            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onClick();
            }
        });
        return new ImageViewBuilder();
    }
    public interface BuilderCallback{
        String onCreate(ImageView view);
        void onClick();

    }
}
