package plin.net.br.plin.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import plin.net.br.plin.R;
import plin.net.br.plin.util.App;
import plin.net.br.plin.util.InternetCheck;
import plin.net.br.plin.util.NotifyNewPost;

public class MainActivity extends Activity {

    private MyWebView webView;
    private Intent intent;
    private ProgressDialog progressDialog;


    private LinearLayout layoutNoInternet;
    private LinearLayout layoutErroServer;

    private final Activity activity = this;
    private boolean erroFlag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (MyWebView) findViewById(R.id.webView);
        layoutNoInternet = (LinearLayout)findViewById(R.id.layout_no_internet);
        layoutErroServer = (LinearLayout)findViewById(R.id.layout_erro_server);

        configWebView();

        load();

    }



    public void reload(View v){
        load();
    }

    private void load(){

        if(InternetCheck.isConnected()) {

            intent = getIntent();

            String link = intent.getStringExtra(NotifyNewPost.POST_LINK);

            if (link == null) {
                //abre index
                webView.loadUrl(getString(R.string.url_site));
            } else {
                //abre página de notificação
                NotifyNewPost.excludeNotify();
                webView.loadUrl(link);
            }

        }else{
            displayErroInternetOffline();
        }
    }


    private void configWebView(){
        webView.clearCache(true);
        webView.clearHistory();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);



        /****
         * Decisão de projeto
         * foi criado um WebViewClient anônimo abaixo para comunicar
         * os componentes de design com os de tratamento de comportamentos
         * da classe
         */
        webView.setWebViewClient(new WebViewClient(){


            // TRATANDO OS CLIQUES E LINKS
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // String not in Logger.


                if (url != null && InternetCheck.isConnected()) {

                    if(
                            url.startsWith(getString(R.string.shareWhatsapp)) ||
                                    url.startsWith(getString(R.string.shareTwitter)) ||
                                    url.startsWith(getString(R.string.shareGplus)) ||
                                    url.startsWith(getString(R.string.shareFacebook))
                            ){

                        view.getContext().startActivity(
                                new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                        return true;//

                    } else {
                        return false; // não trata a url
                    }

                }
                return false;// não trata a url
            }


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                if(erroFlag)
                    displayNothing();


                erroFlag = false;

                if (progressDialog == null) {
                    // If no progress dialog, make one and set message
                    progressDialog = new ProgressDialog(activity);
                    progressDialog.setMessage(App.getContext().getString(R.string.carregando_plin));
                    progressDialog.show();

                    // Hide the webview while loading

                }

            }

            public void onLoadResource(WebView view, String url) {
                // Check to see if there is a progress dialog

            }


            public void onPageFinished(WebView view, String url) {
                // Page is done loading;
                // hide the progress dialog and show the webview
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }

                if(!erroFlag)
                    displayNoError();

            }


            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                erroFlag = true;
                displayErroServer();
            }

        });

    }



    /**TRATANDO O MONITORAMENTO DA INTERNET **/
    private void displayErroInternetOffline(){
        webView.setVisibility(View.INVISIBLE);
        layoutErroServer.setVisibility(View.INVISIBLE);
        layoutNoInternet.setVisibility(View.VISIBLE);
    }










    private void displayErroServer(){
        webView.setVisibility(View.INVISIBLE);
        layoutNoInternet.setVisibility(View.INVISIBLE);
        layoutErroServer.setVisibility(View.VISIBLE);
    }

    private void displayNoError(){
        layoutNoInternet.setVisibility(View.INVISIBLE);
        layoutErroServer.setVisibility(View.INVISIBLE);
        webView.setVisibility(View.VISIBLE);
    }

    private void displayNothing(){
        layoutNoInternet.setVisibility(View.INVISIBLE);
        layoutErroServer.setVisibility(View.INVISIBLE);
        webView.setVisibility(View.INVISIBLE);
    }


    // ATIVANDO BACK NO CELULAR PARA VOLTAR NO SITE
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

}
