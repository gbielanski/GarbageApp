package pl.example.android.garbageapp.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import pl.example.android.garbageapp.R;
import pl.example.android.garbageapp.data.database.SectorColor;
import pl.example.android.garbageapp.ui.ActivitySectorBlue;
import pl.example.android.garbageapp.ui.ActivitySectorGreen;
import pl.example.android.garbageapp.ui.ActivitySectorYellow;
import pl.example.android.garbageapp.ui.MainActivity;

/**
 * Utility class for notifications
 */

public class NotificationUtils {

    public static final String NOTIFICATION_SECTOR_COLOR = "NOTIFICATION_SECTOR_COLOR";
    private static final String GARBAGE_APP_NOTIFICATION_CHANNEL_ID = "garbage-app-channel";
    private static final int GARBAGE_APP_PENDING_INTENT_ID = 1234;

    private NotificationUtils() {}

    public static void showNotification(Context context, int notificationId, int notificationSectorColor) {
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    GARBAGE_APP_NOTIFICATION_CHANNEL_ID,
                    context.getString(R.string.main_notification_channel_name),
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context, GARBAGE_APP_NOTIFICATION_CHANNEL_ID)
                        .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                        .setSmallIcon(R.mipmap.ic_delete_sweep_black_24dp)
                        .setLargeIcon(largeIcon(context))
                        .setContentTitle(contentTitle(context))
                        .setContentText(contentText(context))
                        .setContentIntent(contentIntent(context, notificationSectorColor))
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    private static PendingIntent contentIntent(Context context, int notificationSectorColor) {
        Intent startActivityIntent = new Intent(context, whichActivityToStart(notificationSectorColor));
        startActivityIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(
                context,
                GARBAGE_APP_PENDING_INTENT_ID,
                startActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static CharSequence contentTitle(Context context) {
        return context.getString(R.string.notification_message_title);
    }

    private static CharSequence contentText(Context context) {
        return context.getString(R.string.tomorrow_garbage_collection_msg);
    }

    private static Bitmap largeIcon(Context context) {
        Resources res = context.getResources();
        Bitmap largeIcon = BitmapFactory.decodeResource(res, R.mipmap.ic_launcher_round);
        return largeIcon;
    }

    private static Class whichActivityToStart(int notificationSectorColor) {
        switch (SectorColor.valueOf(notificationSectorColor)) {
            case GREEN : return ActivitySectorGreen.class;
            case BLUE:  return ActivitySectorBlue.class;
            case YELLOW: return ActivitySectorYellow.class;
            default: return MainActivity.class;
        }
    }
}
