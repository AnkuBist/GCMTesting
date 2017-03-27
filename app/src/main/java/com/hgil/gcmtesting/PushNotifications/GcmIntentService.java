package com.hgil.gcmtesting.PushNotifications;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.hgil.gcmtesting.MainActivity;
import com.hgil.gcmtesting.R;

import java.util.Calendar;
import java.util.Locale;

public class GcmIntentService extends IntentService {

    public static final int NOTIFICATION_ID = 2;
    NotificationCompat.Builder builder;
    private NotificationManager mNotificationManager;
    private String TAG = "Fashom";
    private String message;

    public GcmIntentService() {
        super("GcmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        String messageType = gcm.getMessageType(intent);


        if (!extras.isEmpty()) {
            if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                // Post notification of received message.
                sendNotification(extras.getString("message"));
            }
        }
        // Release the wake lock provided by the WakefulBroadcastReceiver.
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

    // Put the message into a notification and post it.
    // This is just one simple example of what you might choose to do with
    // a GCM message.
    private void sendNotification(String msg) {
        mNotificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);
        message = msg;
        Log.e("Notification Arrived", message);

        showNotification(new Intent(this, MainActivity.class));
    }

    private void showNotification(Intent notificationIntent) {
        notificationIntent.putExtra("show_notification", true);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                this)
                .setSmallIcon(R.drawable.harvest_logo)
                .setAutoCancel(true)
                .setWhen(
                        Calendar.getInstance(Locale.getDefault())
                                .getTimeInMillis())
                .setContentTitle("TESt")
                .setStyle(
                        new NotificationCompat.BigTextStyle().bigText(message))
                .setContentText(message);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

}
