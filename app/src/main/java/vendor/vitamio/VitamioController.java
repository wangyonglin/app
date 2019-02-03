package vendor.vitamio;

import android.app.Activity;
import android.content.Context;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;


import com.khbd.app.R;

import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class VitamioController extends MediaController {

    private String mTitle;
    private GestureDetector mGestureDetector;
    private ImageButton img_back;
    private TextView textViewTime;
    private TextView textViewTitle;
    private VideoView videoView;
    private Activity activity;
    private Context context;
    private View mRoot;
    private int controllerWidth = 0;//设置mediaController高度为了使横屏时top显示在屏幕顶端

    //返回监听
    private View.OnClickListener backListener = new View.OnClickListener() {
        public void onClick(View v) {
            if (activity != null) {
                activity.finish();
            }
        }
    };

    //videoview 用于对视频进行控制的等，activity为了退出
    public VitamioController(Context context, VideoView videoView, Activity activity,View container) {
        super(context);
        this.context = context;
        this.videoView = videoView;
        this.activity = activity;
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        controllerWidth = wm.getDefaultDisplay().getWidth();

            mGestureDetector = new GestureDetector(context, new MyGestureListener());
    }



    @Override
    protected View makeControllerView() {
        //加入布局文件,mymediacontroller布局名称
       // View v = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(getResources().getIdentifier("mediacontroller", "layout", getContext().getPackageName()), this);
        View v = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.mediacontroller, this);
        /*
        v.setMinimumHeight(controllerWidth);
        img_back = (ImageButton) v.findViewById(getResources().getIdentifier("mediacontroller_top_back", "id", context.getPackageName()));
        img_back.setOnClickListener(backListener);
        textViewTime = (TextView) v.findViewById(getResources().getIdentifier("mediacontroller_time", "id", context.getPackageName()));
        textViewTitle = (TextView) v.findViewById(getResources().getIdentifier("mediacontroller_filename", "id", context.getPackageName()));
        if (textViewTitle != null) {
            textViewTitle.setText(this.getTitle());
        }*/
        return v;

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
//        System.out.println("MYApp-MyMediaController-dispatchKeyEvent");
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mGestureDetector.onTouchEvent(event)) return true;
        // 处理手势结束
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            //当收拾结束，并且是单击结束时，控制器隐藏/显示
            toggleMediaControlsVisiblity();
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        //双击暂停或开始
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            playOrPause();
            return true;
        }
    }
    //title

    //设置时间
    public void setTime(String time) {

        if (textViewTime != null)
            textViewTime.setText(time);
    }

    public void setTitle(String title) {
        this.mTitle=title;
    }

    private String getTitle() {
        return mTitle;
    }

    //隐藏/显示
    private void toggleMediaControlsVisiblity() {
        if (isShowing()) {
            hide();
        } else {
            show();
        }
    }

    //播放与暂停
    private void playOrPause() {
        if (videoView != null)
            if (videoView.isPlaying()) {
                videoView.pause();
            } else {
                videoView.start();
            }
    }
}