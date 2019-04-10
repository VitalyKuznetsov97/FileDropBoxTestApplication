package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.ui.adapter.view_holder;

import android.view.View;
import android.widget.TextView;

import com.vitaly_kuznetsov.file_dropbox_test_application.R;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.ErrorModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.IModel;

import androidx.annotation.NonNull;

public class ErrorViewHolder extends IViewHolder {

    private TextView message;

    public ErrorViewHolder(@NonNull View itemView) {
        super(itemView);
        message = itemView.findViewById(R.id.text_message);
    }

    @Override
    public void bind(IModel model) {
        if (model instanceof ErrorModel){
            message.setText(((ErrorModel) model).getMessage());
        }
    }

}
