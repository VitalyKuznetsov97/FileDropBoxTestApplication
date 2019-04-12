package com.vitaly_kuznetsov.file_dropbox_test_application.data.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.vitaly_kuznetsov.file_dropbox_test_application.data.application.MyApplication;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.repository.IUserRepository;

import static com.vitaly_kuznetsov.file_dropbox_test_application.data.constants.DataConstants.ACCESS_TOKEN;
import static com.vitaly_kuznetsov.file_dropbox_test_application.data.constants.DataConstants.SHARED_PREFS_PACKAGE;

public class UserRepository implements IUserRepository {

    @Override
    public String getEmail() {
        SharedPreferences prefs =
                MyApplication.getContext().getSharedPreferences(SHARED_PREFS_PACKAGE, Context.MODE_PRIVATE);
        String accessToken = prefs.getString(ACCESS_TOKEN, null);

        if (accessToken == null) return null;
        else {
            DbxRequestConfig config = new DbxRequestConfig("dropbox/sample-app", "en_US");
            DbxClientV2 dbxClient = new DbxClientV2(config, accessToken);
            try {
                String result = dbxClient.users().getCurrentAccount().getEmail();
                result += "\n" + dbxClient.users().getCurrentAccount().getReferralLink();
                return result;
            } catch (DbxException e) {
                return null;
            }
        }
    }
}
