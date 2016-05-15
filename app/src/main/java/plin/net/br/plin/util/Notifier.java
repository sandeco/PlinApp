package plin.net.br.plin.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.NotificationCompat;

import plin.net.br.plin.R;

/**
 * Created by sandeco on 10/05/16.
 */
public class Notifier {

    private static int NOTIFICATION_ID = 120;

    public static void notify(String title, String message) {
        NotificationCompat.Builder builder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(App.getContext())
                        .setSmallIcon(R.drawable.plin_notify)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setLargeIcon(BitmapFactory.decodeResource(App.getContext().getResources(), R.drawable.plin_notify))
                        .setAutoCancel(true)
                        .setLights(Color.WHITE, 1000, 2000);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            builder.setVisibility(Notification.VISIBILITY_PUBLIC);


        NotificationManager notifyManager =
                (NotificationManager) App.getContext().getSystemService(Context.NOTIFICATION_SERVICE);

        notifyManager.notify(NOTIFICATION_ID, builder.build());
    }
}
