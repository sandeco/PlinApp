package plin.net.br.plin.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

import plin.net.br.plin.util.App;

public class MyScheduleReceiver extends BroadcastReceiver {

    // restart service every 30 seconds
    private static final long REPEAT_TIME = 1000 * 60 * 1;
    private static int id = 1;
    private static String PID = "processo";
    private static boolean running = false;


    public MyScheduleReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        int process = intent.getIntExtra(PID,0);

        if(!running){
            Toast.makeText(context,"Rodando",Toast.LENGTH_SHORT).show();
            running = true;
            process = 1;
        }

        if(process==1) {

            Intent alarmIntent = new Intent(context, MyScheduleReceiver.class);
            alarmIntent.putExtra(PID,id);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);

            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), REPEAT_TIME, pendingIntent);

            FindNewPostTask.getInstance().getPostInternet();


        }


    }
}
