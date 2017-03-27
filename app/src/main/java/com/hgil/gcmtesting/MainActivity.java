package com.hgil.gcmtesting;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    String SENDER_ID = "906781634352";
    private GoogleCloudMessaging gcm;
    private String regid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get device registration id here only
        registerInBackground();


    }

    // If app is first time opened or app is updated, senderId must be
    // re-registered for the gcm
    private void registerInBackground() {
        new AsyncTask<Void, String, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging
                                .getInstance(MainActivity.this);
                    }
                    regid = gcm.register(SENDER_ID);
                    Log.e("TEG", "doInBackground: " + regid);
                    //storeRegistrationId(MainActivity.this, regid);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

  /*  // Store the registrationId in the cash
    private void storeRegistrationId(Context context, String regId) {
        //((FashomApplication) getApplication()).setDeviceToken(regId);
        int appVersion;
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            appVersion = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Could not get package name: " + e);
        }
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(PROPERTY_REG_ID, regId);
        editor.putInt(PROPERTY_APP_VERSION, appVersion);
        editor.apply();
    }*/

}
