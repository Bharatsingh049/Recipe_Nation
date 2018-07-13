package com.example.bullet.recipesearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class how2prep extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how2prep);
        String URL=getIntent().getStringExtra("URL");
        WebView view=(WebView)findViewById(R.id.web_view);
        view.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
        view.getSettings().setJavaScriptEnabled(true);
        //myWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        view.setWebViewClient(new WebViewClient());
        view.loadUrl(URL);

    }
}
