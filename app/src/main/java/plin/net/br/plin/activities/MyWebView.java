package plin.net.br.plin.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by sandeco on 05/04/16.
 */
class MyWebView extends WebViewClient {


    private final String FACEBOOK   = "com.facebook.katana";
    private final String TWITTER    = "com.twitter.android";
    private final String INSTAGRAM  = "com.instagram.android";
    private final String GOOGLEPLUS = "com.plus.google";

    private Context context;

    public MyWebView(Context context){
        this.context = context;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
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




    private void sharingToSocialMedia(String application, String title, String text) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setPackage(application);

        shareIntent.putExtra(android.content.Intent.EXTRA_TITLE, "");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "");
        // Start the specific social application
        context.startActivity(shareIntent);
    }


    @Override
    public void onPageFinished(WebView view, String url) {

    }

}