package plin.net.br.plin.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
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


    private void displayErroNoInternet(){
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

    public void reload(View v){
        load();
    }

    private void load(){

        intent = getIntent();

        String link = intent.getStringExtra(NotifyNewPost.POST_LINK);

        if(link==null){
            //abre index
            webView.loadUrl("http://192.168.25.24/plin/");
        }else{
            //abre página de notificação
            NotifyNewPost.excludeNotify();
            webView.loadUrl(link);
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


            public void onLoadResource(WebView view, String url) {
                // Check to see if there is a progress dialog
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
