package pl.example.android.garbageapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.onesignal.OSNotification;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import pl.example.android.garbageapp.ui.MainActivity;

public class GarbageNotificationReceivedHandler implements OneSignal.NotificationReceivedHandler {
    private Context context;

    public GarbageNotificationReceivedHandler(Context context) {

        this.context = context;
    }

    @Override
    public void notificationReceived(OSNotification notification) {
        JSONObject data = notification.payload.additionalData;
        String customKey;

        if (data != null) {
            customKey = data.optString("customkey", null);
            if (customKey != null)
                Log.i("OneSignalExample", "customkey set with value: " + customKey);

        }
    }
}
