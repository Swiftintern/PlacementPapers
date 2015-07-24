package com.example.chhavi.swiftintern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.chhavi.swiftintern.R;
import com.example.chhavi.swiftintern.Utility.AppPreferences;

/**
 * Created by chhavi on 11/7/15.
 */
public class SplashScreen extends Activity implements Animation.AnimationListener{
    private ImageView mainImage;
    private Animation animFadeIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_screen_layout);
       /* mainImage = (ImageView)findViewById(R.id.main_image);
        animFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in_anim);
        animFadeIn.setAnimationListener(this);
        mainImage.setAnimation(animFadeIn);*/
        Thread splashThread = new Thread(){

            @Override
            public void run() {

                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Intent i;
                    if(AppPreferences.isLoggedIn(SplashScreen.this)) {
                         i = new Intent(SplashScreen.this, CompaniesList.class);
                    }else{
                         i = new Intent(SplashScreen.this, LogInActivity.class);
                    }
                    startActivity(i);
                    finish();
                }

            }
        };
        splashThread.start();
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
