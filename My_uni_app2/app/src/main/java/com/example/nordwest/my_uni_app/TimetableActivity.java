package com.example.nordwest.my_uni_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TimetableActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mWebView = new WebView(this);
        setContentView(mWebView);
        mWebView.setWebViewClient(new WebViewSampleClient());
        mWebView.loadUrl("https://myqahe.qabusinessschool.com/Account/StudentTimetable.aspx");
        mWebView.getSettings().setJavaScriptEnabled(true);


    }

    public boolean onKeyDown (int keyCode, KeyEvent event){
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()){
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class WebViewSampleClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return true;
        }
    }
}
