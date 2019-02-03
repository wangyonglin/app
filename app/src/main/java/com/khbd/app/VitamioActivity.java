package com.khbd.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;

import android.os.Bundle;

import android.os.StrictMode;
import android.text.Html;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TextView;


import com.khbd.data.Video;
import com.util.ParamUtil;

import com.vendor.design.MetricsBuilder;


import com.vendor.design.TextViewBuilder;
import com.vendor.network.APIClient;



import com.wang.avi.AVLoadingIndicatorView;
import com.wangyonglin.sdk.Advertising;


import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import javakit.result.ResultCallback;

import vendor.android.ActivityBuilder;

import vendor.vitamio.VitamioPlayer;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class VitamioActivity extends ActivityBuilder {
    private   MediaController mc;
    private VitamioPlayer vitamioPlayer;
    private VideoView mVideoView;
    private FrameLayout fl_controller;
    private AVLoadingIndicatorView load;
    private String mUUID =null;
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        hideNavigationBar();
        super.onSaveInstanceState(outState);
    }

    @SuppressLint("NewApi")  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout your_original_layout =(LinearLayout)LayoutInflater.from(this).inflate(R.layout.activity_vitamio,null);
        setContentView(your_original_layout);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        load = (AVLoadingIndicatorView)your_original_layout.findViewById(R.id.load);

        load.show();

        ParamUtil.pop(VitamioActivity.this, new ParamUtil.ParamCallback() {
            @Override
            public void get(String uuid) {
                mUUID=uuid;
            }
        });


        mVideoView = (VideoView) findViewById(R.id.buffer);
        fl_controller = (FrameLayout)findViewById(R.id.fl_controller);
        mc = new MediaController(this,true,fl_controller);
        vitamioPlayer = new VitamioPlayer(mVideoView,mc);

        APIClient.id(mUUID, new ResultCallback<Video>() {
            @Override
            public void reject(RuntimeException e) {
                e.printStackTrace();
                load.hide();
            }

            @Override
            public void resove(Video cls) {
                //title
                TextViewBuilder.onCreateView(VitamioActivity.this,findViewById(R.id.film_title),new TextViewBuilder.BuilderCallback(){

                    @Override
                    public void onCreate(Context context, TextView view) {
                        view.setText(cls.getTitle());

                    }
                });
                //
                TextViewBuilder.onCreateView(VitamioActivity.this,findViewById(R.id.film_overview),new TextViewBuilder.BuilderCallback(){

                    @Override
                    public void onCreate(Context context, TextView view) {

                            view.setText(Html.fromHtml(cls.getOverview()));
                    }
                });
                vitamioPlayer.run(cls.getVideo()).play();
                load.hide();
            }
        });



        mc.setOnControllerClick(new MediaController.OnControllerClick() {
            @Override
            public void OnClick(int type) {
                if (type == 0) {
                    if(getRequestedOrientation()== ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
                        MetricsBuilder.created(VitamioActivity.this, new MetricsBuilder.DisplayMetricsCallback() {
                            @Override
                            public void metrics(float density, int widthPixels, int heightPixels, float xdpi, float ydpi) {
                                logInfo("转横屏中"+widthPixels+"DDDD"+heightPixels);
                                fl_controller.setLayoutParams(new LinearLayout.LayoutParams(heightPixels,widthPixels));

                            }
                        });

                        hideNavigationBar();

                        your_original_layout.setBackgroundColor(getResources().getColor(R.color.colorBlack));
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    }else{

                        MetricsBuilder.created(VitamioActivity.this, new MetricsBuilder.DisplayMetricsCallback() {
                            @Override
                            public void metrics(float density, int widthPixels, int heightPixels, float xdpi, float ydpi) {
                                logInfo("转竖屏中"+widthPixels+"DDDD"+heightPixels);

                                fl_controller.setLayoutParams(new LinearLayout.LayoutParams(heightPixels,dip2px(VitamioActivity.this,200)));
                            }
                        });


                        showNavigationBar();
                        your_original_layout.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    }



                }
            }
        });


        Advertising.Create(VitamioActivity.this, new Advertising.AdvertisingCallback() {
            @Override
            public ImageView loadImageView() {
                return (ImageView)findViewById(R.id.ADSIMAGE);
            }
        });


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        //屏幕切换时，设置全屏
        if (mVideoView != null){
            mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        }
        super.onConfigurationChanged(newConfig);
    }



}
