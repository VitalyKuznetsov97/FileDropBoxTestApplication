package com.vitaly_kuznetsov.file_dropbox_test_application.data.repository;

import android.content.Context;
import android.content.SharedPreferences;
import com.dropbox.core.android.Auth;
import com.vitaly_kuznetsov.file_dropbox_test_application.data.application.MyApplication;

import static com.vitaly_kuznetsov.file_dropbox_test_application.data.constants.DataConstants.ACCESS_TOKEN;
import static com.vitaly_kuznetsov.file_dropbox_test_application.data.constants.DataConstants.APP_KEY;
import static com.vitaly_kuznetsov.file_dropbox_test_application.data.constants.DataConstants.SHARED_PREFS_PACKAGE;

public class DpbxRepository {

    public static void auth(Context context){
        Auth.startOAuth2Authentication(context, APP_KEY);
    }

    public static void checkLogged(){
        String accessToken = Auth.getOAuth2Token();
        if (accessToken != null) {
            SharedPreferences prefs = MyApplication.getContext().getSharedPreferences(SHARED_PREFS_PACKAGE, Context.MODE_PRIVATE);
            prefs.edit().putString(ACCESS_TOKEN, accessToken).apply();
        }
    }
}
