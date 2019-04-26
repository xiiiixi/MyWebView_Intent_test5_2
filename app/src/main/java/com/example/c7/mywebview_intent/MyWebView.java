package com.example.c7.mywebview_intent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by c7 on 2019/4/17.
 */

public class MyWebView extends AppCompatActivity{
    private WebView webView;
    private String url;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mywebview);
        initView();
        setWebView();
    }

    private void initView(){
        webView=(WebView) findViewById(R.id.my_webView);
        url=getIntent().getData().toString();
    }

    private void setWebView(){
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);

        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view,String url){
                view.loadUrl(url);
                return true;
            }
        });
    }
}
