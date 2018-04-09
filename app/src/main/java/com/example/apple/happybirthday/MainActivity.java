package com.example.apple.happybirthday;

import android.annotation.TargetApi;
import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import com.example.apple.happybirthday.util.AssetsReadHelper;

public class MainActivity extends Activity {

    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    private GLSurfaceView glSurfaceView;
    private TheRender render;
    float yuanx = 0;
    float yuany = 0;

    TextView textView;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_main);

        render = new TheRender(this);
//        glSurfaceView = new GLSurfaceView(this);

        glSurfaceView = (GLSurfaceView) findViewById(R.id.gl);
        glSurfaceView.setAlpha(0);
        glSurfaceView.setEGLContextClientVersion(2);
        glSurfaceView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    float x = event.getX();
                    float y = event.getX();

                    x = yuanx - x;
                    y = yuany - y;
                    Log.v("this", "x:" + x + "  y:" + y);
                    render.setXY(x, y);
                }
                yuanx = event.getX();
                yuany = event.getY();
                return true;
            }
        });

        glSurfaceView.setRenderer(render);

        textView = (TextView) findViewById(R.id.text);
//        textView.setText("仅此献给我记忆中的女神---");
        textView.setText("仅此献给唯一的UI妹纸---");
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onAnimationEnd(Animation animation) {
                textView.setText("小业");
                AlphaAnimation anim2 = new AlphaAnimation(0, 1);
                anim2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        textView.setVisibility(View.GONE);
                        glSurfaceView.setAlpha(1);
                        render.start();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                anim2.setDuration(7000);
                textView.setAnimation(anim2);


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        alphaAnimation.setDuration(7000);
        textView.setAnimation(alphaAnimation);
        alphaAnimation.start();


        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(this, R.raw.happybirthday);
        audioManager = (AudioManager) this.getSystemService(AUDIO_SERVICE);

        mediaPlayer.start();
    }


    @Override
    protected void onPause() {
        super.onPause();
        glSurfaceView.onPause();
        mediaPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        glSurfaceView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }
}
