package com.khbd.app;

import android.os.CountDownTimer;
import android.view.View;

import android.widget.TextView;


public class JavaKitDownTimer extends CountDownTimer {
    public JavaKitDownTimerCallback<String> javaKitDownTimerCallback;
    private long countDownIntervald;
    public JavaKitDownTimer(long millisInFuture, long countDownInterval, TextView view, JavaKitDownTimerCallback<String> callback) {
        super(millisInFuture, countDownInterval);
        countDownIntervald =countDownInterval;
        javaKitDownTimerCallback=callback;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.finish();
                cancel();
            }
        });
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if(millisUntilFinished>=countDownIntervald){
            javaKitDownTimerCallback.tick(millisUntilFinished);
        }else {
            javaKitDownTimerCallback.finish();
            cancel();
        }

    }

    @Override
    public void onFinish() {
        javaKitDownTimerCallback.finish();
        cancel();
    }
    public interface JavaKitDownTimerCallback<T>{
        default public void finish(){};
        default public void tick(long millisUntilFinished){}
    }
}
