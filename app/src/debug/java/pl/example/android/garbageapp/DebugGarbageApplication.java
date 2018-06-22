package pl.example.android.garbageapp;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.onesignal.OneSignal;

/**
 * Created by gbielanski on 1/3/2018.
 */

public class DebugGarbageApplication extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationOpenedHandler(new ExampleNotificationOpenedHandler())
                .init();
    }
}
