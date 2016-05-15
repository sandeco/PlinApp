package plin.net.br.plin.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.NotificationCompat;

import plin.net.br.plin.R;
import plin.net.br.plin.activities.MainActivity;
import plin.net.br.plin.model.Post;

/**
 * Created by sandeco on 15/05/16.
 */
public class NotifyNewPost extends Notifier {


    public static final String POST_LINK = "post_link";
    public static final int NOTIFY_ID = 120;

    public static void notify(Post p){


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(App.getContext());
        mBuilder.setSmallIcon(R.drawable.plin_notify);
        mBuilder.setContentTitle("Plin novidade");
        mBuilder.setContentText(p.getTitle().getRendered());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            mBuilder.setVisibility(Notification.VISIBILITY_PUBLIC);


        Uri sound = Uri.parse("android.resource://" + App.getContext().getPackageName() + "/" + R.raw.plin_notify);
        mBuilder.setSound(sound);


        Intent resultIntent = new Intent(App.getContext(), MainActivity.class);

        resultIntent.putExtra(POST_LINK,p.getLink());

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        App.getContext(),
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setContentIntent(resultPendingIntent);



        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) App.getContext().getSystemService(App.NOTIFICATION_SERVICE);

        // Builds the notification and issues it.
        mNotifyMgr.notify(NOTIFY_ID, mBuilder.build());


    }


    public static void excludeNotify(){
        String ns = App.NOTIFICATION_SERVICE;
        NotificationManager nMgr = (NotificationManager) App.getContext().getSystemService(ns);
        nMgr.cancel(NOTIFY_ID);
    }

}
