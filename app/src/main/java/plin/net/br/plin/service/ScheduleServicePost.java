package plin.net.br.plin.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import plin.net.br.plin.util.App;

/**
 * Created by sandeco on 10/08/16.
 */
public class ScheduleServicePost extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags,int startId) {

        Toast.makeText(App.getContext(),"Buscando plin",Toast.LENGTH_LONG).show();

        FindNewPostTask.getInstance().getPostInternet();
        return START_STICKY;
    }
}
