package com.khbd.data;

import android.content.Context;

import com.khbd.app.R;

import java.util.Iterator;
import java.util.List;

import javakit.network.JavaKitClientResponse;
import javakit.network.JavaKitClientResponseCallback;
import javakit.util.JavaKitJsonUtils;
import javakit.util.URLUtils;

public class WebcamsClient {
    public class ViewHolder{
        public String image;
        public String video;

        public ViewHolder(String image, String video) {
            this.image = image;
            this.video = video;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }
    }
    public static void all(Context context,WebcamsClientCallback callback){
        String uri= context.getString(R.string.url_webcams_all);
        URLUtils urlUtils = URLUtils.make(context.getString(R.string.url_webcams_all));
        JavaKitClientResponse.get(urlUtils.url(), new JavaKitClientResponseCallback<String>() {
            @Override
            public void success(String res) {
                try {
                List<Webcams> webcams = JavaKitJsonUtils.json2list(res,Webcams.class);
                    callback.WebcamsAll(context,webcams);
                } catch (Exception e) {
                    callback.onErrorResume(e);
                }
            }
        });
    }
    public static void categories(Context context,String key,WebcamsClientCallback callback){
        StringBuffer uri= new StringBuffer(context.getString(R.string.url_webcams_categories));
        uri.append("?key=").append(key);
        System.out.print(uri.toString());
        JavaKitClientResponse.get(uri.toString(), new JavaKitClientResponseCallback<String>() {
            @Override
            public void success(String res) {
                System.out.print(res);
                try {
                    List<Webcams> webcams = JavaKitJsonUtils.json2list(res,Webcams.class);
                    callback.WebcamsAll(context,webcams);
                } catch (Exception e) {
                    callback.onErrorResume(e);
                }
            }
        });
    }

        public interface WebcamsClientCallback{
            void WebcamsAll(Context context,List<Webcams> list);
            default void onErrorResume(Exception e){};
        }
}
