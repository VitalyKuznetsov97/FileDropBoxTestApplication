package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.ui.custom_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

import com.vitaly_kuznetsov.file_dropbox_test_application.R;

import static com.vitaly_kuznetsov.file_dropbox_test_application.presentation.constants.PresentationConstants.PROGRESS_BAR_SCALE;

@SuppressLint("ViewConstructor")
public class CustomProgressBar extends ProgressBar {

    public CustomProgressBar(View container, Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setLayoutParams(container.getLayoutParams());
        this.getIndeterminateDrawable().setColorFilter(context.getResources().
                getColor(R.color.primaryDarkColor), PorterDuff.Mode.SRC_IN);
        this.setScaleX(PROGRESS_BAR_SCALE);
        this.setScaleY(PROGRESS_BAR_SCALE);
    }

}
