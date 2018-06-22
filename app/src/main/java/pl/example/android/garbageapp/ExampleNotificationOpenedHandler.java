package pl.example.android.garbageapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import pl.example.android.garbageapp.ui.MainActivity;
import pl.example.android.garbageapp.utils.NotificationUtils;

class ExampleNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
    private final Context context;
    // This fires when a notification is opened by tapping on it.

    public ExampleNotificationOpenedHandler(Context context) {
        this.context = context;
    }
    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        OSNotificationAction.ActionType actionType = result.action.type;
        JSONObject data = result.notification.payload.additionalData;
        String customKey;

        if (data != null) {
            customKey = data.optString("en", null);
            if (customKey != null)
                Log.i("OneSignalExample", "customkey set with value: " + customKey);
        }

        if (actionType == OSNotificationAction.ActionType.ActionTaken)
            Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID);

        //Tutaj trzeba sparsowac
        //if(NotificationUtils.whichActivityToStart())
        // The following can be used to open an Activity of your choice.
        // Replace - getApplicationContext() - with any Android Context.
         Intent intent = new Intent(context, MainActivity.class);
         intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
         context.startActivity(intent);

        // Add the following to your AndroidManifest.xml to prevent the launching of your main Activity
        //   if you are calling startActivity above.
     /*
        <application ...>
          <meta-data android:name="com.onesignal.NotificationOpened.DEFAULT" android:value="DISABLE" />
        </application>
     */
    }
}