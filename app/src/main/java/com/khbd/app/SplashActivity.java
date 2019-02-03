package com.khbd.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;
import com.util.RouteUtil;
import com.util.ToastUtil;

import javakit.result.ResultCallback;
import plugins.google.admob.AdmobBuilder;

public class SplashActivity extends Activity {

    private AdView MEDIUM_RECTANGLE;

    JavaKitDownTimer javaKitDownTimer=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

     //   RouteUtil.createService(getApplicationContext());

        AdmobBuilder.onCreateAdView(SplashActivity.this, findViewById(R.id.MEDIUM_RECTANGLE), new ResultCallback<AdView>() {
            @Override
            public void resove(AdView view) {
                MEDIUM_RECTANGLE = view;
            }

            @Override
            public void reject(RuntimeException e) {
                ToastUtil.showToast(SplashActivity.this, e.getMessage());
            }
        });


        TextView view =(TextView)findViewById(R.id.DownTimer);
        javaKitDownTimer = new JavaKitDownTimer(10000, 1000,view, new JavaKitDownTimer.JavaKitDownTimerCallback<String>() {


            @Override
            public void finish() {
                SplashActivity.this.finish();
                RouteUtil.JumpWhenCanClick(SplashActivity.this, MainActivity.class);
                }

            @Override
            public void tick(long millisUntilFinished) {
                view.setText("广告| "+millisUntilFinished/ 1000 + "秒");

            }
        });

        javaKitDownTimer.start();
    }



    /** Called when leaving the activity */
    @Override
    public void onPause() {
        if (MEDIUM_RECTANGLE != null) {
            MEDIUM_RECTANGLE.pause();
        }
        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();
        if (MEDIUM_RECTANGLE != null) {
            MEDIUM_RECTANGLE.resume();
        }
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
        if (MEDIUM_RECTANGLE != null) {
            MEDIUM_RECTANGLE.destroy();
        }

        javaKitDownTimer.cancel();
       // RouteUtil.destroService(getApplicationContext());
        super.onDestroy();
    }
}

