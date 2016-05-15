package plin.net.br.plin.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {
    public BootReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent it = new Intent(context, NewPostService.class);
        context.startService(it);
    }
}
