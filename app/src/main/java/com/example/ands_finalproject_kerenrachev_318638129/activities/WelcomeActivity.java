package com.example.ands_finalproject_kerenrachev_318638129.activities;

import static android.content.pm.PackageInfo.REQUESTED_PERMISSION_GRANTED;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.ands_finalproject_kerenrachev_318638129.R;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WelcomeActivity extends AppCompatActivity {

    private LottieAnimationView splash_IMG_logo;
    private TextView welcome_TXT_top;
    private TextView welcome_TXT_bottom;

    final int ANIM_DURATION = 1500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_main);

        findViews();
        showViewSlideDown(welcome_TXT_top, false);
        showViewSlideDown(welcome_TXT_bottom, false);
        showViewSlideDown(splash_IMG_logo, true);

    }

    private void findViews() {
        splash_IMG_logo = findViewById(R.id.splash_IMG_logo);
        welcome_TXT_top = findViewById(R.id.welcome_TXT_top);
        welcome_TXT_bottom = findViewById(R.id.welcome_TXT_bottom);
    }


    public void showViewSlideDown(final View v, boolean lastAnim) {
        v.setVisibility(View.VISIBLE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        v.setY(-height / 2);
        v.setScaleY(0.0f);
        v.setScaleX(0.0f);
        v.animate()
                .scaleY(1.0f)
                .scaleX(1.0f)
                .translationY(0)
                .setDuration(ANIM_DURATION)
                .setInterpolator(new AccelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                        if(lastAnim) animationDone();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
    }

    private void animationDone() {
        startApplication();

    }
    private void startApplication() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

}