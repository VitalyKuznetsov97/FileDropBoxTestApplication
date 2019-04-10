package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.navigation;

import android.content.Context;
import android.content.Intent;

import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.ui.activity.ChooseActivity;

/**
 * Class used to navigate through the application.
 */

public class Navigator {

    private Navigator() { }

    /**
     * Goes to the file choosing screen.
     * @param context A Context needed to open the destiny activity.
     */
    public static void navigateToChooseActivity(Context context) {
        if (context != null) {
            Intent intentToLaunch = new Intent(context, ChooseActivity.class);
            context.startActivity(intentToLaunch);
        }
    }

}