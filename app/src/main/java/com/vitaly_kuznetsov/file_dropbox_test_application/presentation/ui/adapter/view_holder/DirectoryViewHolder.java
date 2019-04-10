package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.ui.adapter.view_holder;

import android.view.View;
import android.widget.TextView;

import com.vitaly_kuznetsov.file_dropbox_test_application.R;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.DirectoryModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.IModel;

import androidx.annotation.NonNull;

import static com.vitaly_kuznetsov.file_dropbox_test_application.presentation.constants.PresentationConstants.FILE_STRING;

public class DirectoryViewHolder extends IViewHolder {

    private TextView name;
    private TextView amountOfFiles;

    public DirectoryViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.text_name);
        amountOfFiles = itemView.findViewById(R.id.text_amount);
    }

    @Override
    public void bind(IModel model) {
        if (model instanceof DirectoryModel){
            DirectoryModel directoryModel = (DirectoryModel) model;
            name.setText(directoryModel.getName());
            String string = directoryModel.getAmountOfFiles() + FILE_STRING;
            amountOfFiles.setText(string);
        }
    }

}
