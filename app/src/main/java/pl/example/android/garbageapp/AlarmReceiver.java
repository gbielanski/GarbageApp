package pl.example.android.garbageapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import pl.example.android.garbageapp.utils.NotificationUtils;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("AlarmReceiver", "onReceive");
        String action = intent.getAction();
        if(action != null){
            if(action.equals("android.intent.action.BOOT_COMPLETED") ||
                    action.equals("android.intent.action.QUICKBOOT_POWERON")){
                Log.d("AlarmReceiver", action);
                NotificationUtils.scheduleSectorTermsNotification(context);
            }

        }

    }
}
