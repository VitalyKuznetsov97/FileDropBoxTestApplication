package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.ui.adapter.view_holder;

import android.view.View;
import android.widget.TextView;

import com.vitaly_kuznetsov.file_dropbox_test_application.R;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.FileModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.IModel;

import java.text.SimpleDateFormat;
import java.util.Locale;

import androidx.annotation.NonNull;

public class FileViewHolder extends IViewHolder {

    private TextView name;
    private TextView url;
    private TextView size;
    private TextView date;

    public FileViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.text_name);
        url = itemView.findViewById(R.id.text_url);
        size = itemView.findViewById(R.id.text_size);
        date = itemView.findViewById(R.id.text_date);
    }

    @Override
    public void bind(IModel model) {
        if (model instanceof FileModel){
            FileModel fileModel = (FileModel) model;
            name.setText(fileModel.getName());
            url.setText(fileModel.getUrl());
            size.setText(fileModel.getName());

            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
            date.setText(dateFormat.format(fileModel.getDate()));
        }
    }
}
