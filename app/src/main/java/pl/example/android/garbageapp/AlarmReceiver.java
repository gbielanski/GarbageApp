package pl.example.android.garbageapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import pl.example.android.garbageapp.utils.NotificationUtils;

public class AlarmReceiver extends BroadcastReceiver {
    private static final String LOG_TAG = AlarmReceiver.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(LOG_TAG, "onReceive");
        String action = intent.getAction();
        if(action != null){
            if(action.equals("android.intent.action.BOOT_COMPLETED") ||
                    action.equals("android.intent.action.QUICKBOOT_POWERON")){
                Log.d(LOG_TAG, action);
                NotificationUtils.scheduleNotificationIntentServiceTriggering(context);
            }
        }
    }
}
