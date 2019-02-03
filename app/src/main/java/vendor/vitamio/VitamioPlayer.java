package vendor.vitamio;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.khbd.app.R;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;

import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class VitamioPlayer  implements MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener{
    //private VitamioController mVitamioController;

    private VideoView mVideoView;
    public VitamioPlayer run(String url){
        mVideoView.setVideoPath(url);
        return this;
    }
    public void play(){
        this.OnPreparedListener();
        this.playOrPause();
    }
    public  VitamioPlayer(VideoView videoView ,MediaController mediaController){
        this.mVideoView=videoView;
        //mVideoView.setVideoPath(url);
        mVideoView.setMediaController(mediaController);
        mediaController.setVisibility(View.GONE);
        mVideoView.requestFocus();
      //  this.OnPreparedListener();
       // this.playOrPause();
    }
    public VitamioPlayer(VideoView videoView,VitamioController vitamioController){
        this.mVideoView=videoView;
       // mVideoView.setVideoPath(url);
        mVideoView.setMediaController(vitamioController);
        mVideoView.requestFocus();

        this.OnPreparedListener();
        this.playOrPause();
    }

    //获取系统当前时间
   // public void setCurrentTime(String time);

    //获取缓冲值
    public void OnBufferingUpdateListener(final BufferCallback callback){
        mVideoView.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                String buff= "已缓冲：" + percent + "%";
                callback.toString(buff);
            }
        });

    }
    public void OnInfoListener(final VitamioNetSpeedCallback vitamioNetSpeedCallback){

        mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what) {
                    //开始缓冲
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        vitamioNetSpeedCallback.NetSpeedVisibility(View.VISIBLE);
                        vitamioNetSpeedCallback.PercentVisibility(View.VISIBLE);
                        mp.pause();
                        break;
                    //缓冲结束
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        vitamioNetSpeedCallback.NetSpeedVisibility(View.GONE);
                        vitamioNetSpeedCallback.PercentVisibility(View.GONE);
                        mp.start();
                        break;
                    //正在缓冲
                    case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                        String speed = "当前网速:" + extra + "kb/s";
                        vitamioNetSpeedCallback.toString(speed);
                        break;
                }
                return true;
            }
        });

    }
    private void OnPreparedListener(){
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                // optional need Vitamio 4.0
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });
    }


    //播放与暂停
    private void playOrPause() {
        if (mVideoView != null)
            if (mVideoView.isPlaying()) {
                mVideoView.pause();
            } else {
                mVideoView.start();
            }
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        return false;
    }
}
