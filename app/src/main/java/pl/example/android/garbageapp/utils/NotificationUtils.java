package pl.example.android.garbageapp.utils;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;

import pl.example.android.garbageapp.R;
import pl.example.android.garbageapp.data.database.SectorTermNotificationIntentService;

/**
 * Created by miltomasz on 15/11/17.
 */

public class NotificationUtils {
    private static final int SIX_PM = 18;
    private static final int ZERO = 00;

    public static final String NOTIFICATION_SECTOR_COLOR = "NOTIFICATION_SECTOR_COLOR";

    private NotificationUtils() {}

    public static void showNotification(Context context, int notificationId, String contentText) {
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context, NotificationChannel.DEFAULT_CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_delete_sweep_black_24dp)
                        .setContentTitle("Informacja")
                        .setContentText(contentText);
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    public static void scheduleSectorTermsNotification(Context context) {
        Log.d("NotificationUtils", "Schedule alarm manager for counting sector terms");
        Intent intent = new Intent(context.getApplicationContext(), SectorTermNotificationIntentService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context.getApplicationContext(), 0, intent, 0 );
        AlarmManager alarmManager = (AlarmManager) context.getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = getCalendarForNotification();
        // set up alarm manager to repeat
        alarmManager.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY,
                pendingIntent);
    }

    /**
     * Prepares exact date (hours, minutes, seconds) for {@code {@link AlarmManager}}
     * @return date represented as {@code {@link Calendar}}
     */
    @NonNull
    private static Calendar getCalendarForNotification() {
        // set the alarm to start at approximately 6:00 p.m.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, SIX_PM);
        calendar.set(Calendar.MINUTE, ZERO);
        calendar.set(Calendar.SECOND, ZERO);
        Log.d("NotificationUtils", "Alarm manager time scheduled for: "
                + SectorTermsDateUtils.getNextAlarmTime(calendar.getTimeInMillis()));
        return calendar;
    }
}
