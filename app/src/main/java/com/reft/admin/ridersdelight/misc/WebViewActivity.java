package com.reft.admin.ridersdelight.misc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.reft.admin.ridersdelight.R;

/**
 * Created by arun on 4/19/2016.
 */
public class WebViewActivity extends Activity {

    String mapUrl;
    WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        setFinishOnTouchOutside(false);
        Intent i = getIntent();
        mapUrl = i.getStringExtra(Constants.TAG_URL);
        webView = (WebView) findViewById(R.id.webView_url);
        startWebView(mapUrl);
    }

    private void startWebView(String url) {

        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
        finish();
        return;
    }
}
