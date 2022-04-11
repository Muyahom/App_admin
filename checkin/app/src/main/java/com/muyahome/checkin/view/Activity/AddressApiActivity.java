package com.muyahome.checkin.view.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.muyahome.checkin.R;

public class AddressApiActivity extends AppCompatActivity {

    private WebView webView;

    class MyJavaScriptInterface
    {
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void processDATA(String data) {
            Bundle extra = new Bundle();
            Intent intent = new Intent();
            extra.putString("data", data);
            intent.putExtras(extra);
            setResult(RESULT_OK, intent);
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_webview);

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "Android");
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                webView.loadUrl("javascript:sample2_execDaumPostcode();");
            }
        });

        webView.loadUrl("https://moyahome-23245.web.app");
    }
}
