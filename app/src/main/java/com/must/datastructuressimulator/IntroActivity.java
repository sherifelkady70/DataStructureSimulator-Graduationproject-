package com.must.datastructuressimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewAnimator;

public class IntroActivity extends AppCompatActivity {


    private static final int SPLASH_SCREEN = 8000;

    //Animation topAnim, bottomAnim;
    ImageView appLogo;
    TextView appNameP1, appNameP2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIHelper.hideActivityStatusBar(getWindow());
        setContentView(R.layout.activity_intro);

        //topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        //bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        appLogo = findViewById(R.id.iv_app_logo);
        appNameP1 = findViewById(R.id.tv_app_name_p1);
        appNameP2 = findViewById(R.id.tv_app_name_p2);


        //appLogo.setAnimation(topAnim);
        //appName.setAnimation(bottomAnim);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //ViewAnimator.animate(appLogo).scale(1.35F).duration(5000).start();
                com.github.florent37.viewanimator.ViewAnimator.animate(appLogo).scale(1.25F).newsPaper().flipHorizontal().duration(5000).start();
                com.github.florent37.viewanimator.ViewAnimator.animate(appNameP1).bounceIn().flash().duration(5000).start();
                ViewAnimator.animate(appNameP2).slideBottom().duration(5000).start();
            }
        });


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
                finish();
            }
        }, SPLASH_SCREEN);

    }
}