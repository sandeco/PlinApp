package plin.net.br.plin.activities;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

import plin.net.br.plin.util.InternetCheck;

/**
 * Created by sandeco on 19/05/16.
 */

public class MyWebView extends WebView {
    public MyWebView(Context context) {
        super(context);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void loadUrl(String url) {
        if(InternetCheck.isConnected())
            super.loadUrl(url);
    }



}
