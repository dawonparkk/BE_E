package com.example.be_e;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public class SplashAcitivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        ImageView splash = (ImageView) findViewById(R.id.gif_splash);
        GlideDrawableImageViewTarget giflmage = new GlideDrawableImageViewTarget(splash);
        Glide.with(this).load(R.drawable.splash).into(giflmage);

        Handler hd = new Handler();
        hd.postDelayed(new splashhandler(), 3000);
    }

    private class splashhandler implements Runnable {
        @Override
        public void run() {
            startActivity(new Intent(getApplication(), UserSelect.class));
            SplashAcitivity.this.finish();
        }
    }

    public void onBackPressed() {

    }
}
