package plin.net.br.plin.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by sandeco on 10/05/16.
 */
public class App extends Application {
    public static final String TAG = "Inspector";
    private static App singleton;

    public App() {
        super();

        singleton = this;
    }

    public static Context getContext() {
        return singleton;
    }

    public static SharedPreferences getPreferences() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());

        return prefs;
    }
}
