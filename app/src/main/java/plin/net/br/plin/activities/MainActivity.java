package plin.net.br.plin.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;

import plin.net.br.plin.R;
import plin.net.br.plin.activities.MyWebView;
import plin.net.br.plin.util.NotifyNewPost;

public class MainActivity extends Activity {

    private WebView webView;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webView);

        webView.setWebViewClient(new MyWebView(this));

        webView.clearCache(true);
        webView.clearHistory();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

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


    private boolean isPlinOnline(){

        return false;

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
