package com.example.nordwest.my_uni_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebDashboardActivity extends AppCompatActivity {


    private WebView mWebView;

    /*WebView will open and load provided lick OnCreate method.
     Meaning the web dashboard will be launched at the same tie when the activity is called.*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWebView = new WebView(this);
        setContentView(mWebView);
        mWebView.setWebViewClient(new WebViewSampleClient());
        mWebView.loadUrl("https://partnerships.moodle.roehampton.ac.uk/login/index.php");
        mWebView.getSettings().setJavaScriptEnabled(true);
    }

    /*If the back key is pressed and the activity allows one step back and the OS will do so.
    Meaning if the back key is pressed and earlier launched activities are still open (onPasse) they are going to be relaunched
    steeply*/
    public boolean onKeyDown (int keyCode, KeyEvent event){
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()){
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /*
    As the link was loaded by calling method loadUrl() then method shouldOverrideUrlLoading() calls back for this
    type of link on the page, as the given link is absolute.
    For example, the used URL is "http://www.example.com/showProfile" instead of just a relative link "showProfile".
    */
    private class WebViewSampleClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return true;
        }
    }

}
