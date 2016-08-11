package plin.net.br.plin.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import plin.net.br.plin.util.App;

public class NewPostService extends Service {

    public NewPostService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags,int startId) {
        FindNewPostTask.getInstance().start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {

    }
}
