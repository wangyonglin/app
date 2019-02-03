package com.vendor;

import android.content.Context;
import android.view.View;

import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import vendor.vitamio.VitamioPlayer;

public class VitamioBuilder {
    private static MediaController mediaController;
    public  static VitamioBuilder create(Context context,boolean fromXml,View container, BuilderCallback callback){
         mediaController = new MediaController(context,fromXml,container);
             mediaController.setOnControllerClick(new MediaController.OnControllerClick() {
            @Override
            public void OnClick(int type) {
                if(type == 0){
                    callback.Landscape();
                }else {
                    callback.Portrait();
                }
            }
        });
        return null;
    }

    public void setVitamioPlayer(){

    }
    public interface BuilderCallback{
        public void Portrait();
        public void Landscape();
    }
}
