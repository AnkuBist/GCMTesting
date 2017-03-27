package com.hgil.gcmtesting;

/**
 * Created by mohan.giri on 27-03-2017.
 */

public interface Config {


    // CONSTANTS
    static final String YOUR_SERVER_URL =
            "YOUR_SERVER_URL/gcm_server_files/register.php";

    // Google project id
    static final String GOOGLE_SENDER_ID = "9432667788990";

    /**
     * Tag used on log messages.
     */
    static final String TAG = "GCM Android Example";

    static final String DISPLAY_MESSAGE_ACTION =
            "com.androidexample.gcm.DISPLAY_MESSAGE";

    static final String EXTRA_MESSAGE = "message";


}
