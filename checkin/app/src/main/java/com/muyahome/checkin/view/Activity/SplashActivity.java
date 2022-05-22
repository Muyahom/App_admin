package com.muyahome.checkin.view.Activity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.muyahome.checkin.R;

public class SplashActivity extends AppCompatActivity {
    private Handler mHadler = new Handler();
    private View mDecorView;
    WebView webView;
    WebSettings webSettings;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        webView = findViewById(R.id.splash);
        webView.setWebViewClient(new WebViewClient()); //클릭시 새창 뜨지않게
        webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/splash.html");



        mHadler.postDelayed(new SplashHandler(), 4000);




    }

    private class SplashHandler implements Runnable{
        public void run(){
            startActivity(new Intent(getApplication(), MainActivity.class));
            SplashActivity.this.finish();
        }

    }

    //스플래시 화면에서 넘어갈때 뒤로가기 막기
    public void onBackPressed(){

    }

    }
