package plin.net.br.plin.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootReceiver extends BroadcastReceiver {

    public BootReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {


        Toast.makeText(context,"Plin ativo",Toast.LENGTH_SHORT).show();

        Intent it = new Intent(context, NewPostService.class);
        context.startService(it);
    }
}
