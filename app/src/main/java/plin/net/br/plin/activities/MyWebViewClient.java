package plin.net.br.plin.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by sandeco on 05/04/16.
 */
class MyWebViewClient extends WebViewClient {


    private final String FACEBOOK   = "com.facebook.katana";
    private final String TWITTER    = "com.twitter.android";
    private final String INSTAGRAM  = "com.instagram.android";
    private final String GOOGLEPLUS = "com.plus.google";

    private Context context;

    public MyWebViewClient(Context context){
        this.context = context;
    }







    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        // String not in Logger.
        if (url != null) {

            if(
                url.startsWith("whatsapp://") ||
                url.startsWith("https://twitter.com/") ||
                url.startsWith("https://plus.google.com/") ||
                url.startsWith("https://www.facebook.com/")
            ){

                view.getContext().startActivity(
                        new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                return true;

            } else {
                return false;
            }

        }
        return false;
    }



    @Override
    public void onPageFinished(WebView view, String url) {

    }

}