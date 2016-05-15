package plin.net.br.plin.util;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by sandeco on 10/05/16.
 */
public class InternetCheck {

    public static boolean isConnected() {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        conectado = conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected();
        return conectado;
    }
}
