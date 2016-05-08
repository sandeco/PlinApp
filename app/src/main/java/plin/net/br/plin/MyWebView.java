package plin.net.br.plin;

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

            if (url.startsWith("whatsapp://")) {
                view.getContext().startActivity(
                        new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

                /*
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.whatsapp");
                context.startActivity(sendIntent);*/

                return true;
            } else if (url.startsWith("https://www.facebook.com")) {

                this.sharingToSocialMedia(FACEBOOK,"Titulo facebool","aqui é um compartilhamento do facebook");

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