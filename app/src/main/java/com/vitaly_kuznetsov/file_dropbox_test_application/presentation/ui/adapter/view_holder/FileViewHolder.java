package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.ui.adapter.view_holder;

import android.view.View;
import android.widget.TextView;

import com.vitaly_kuznetsov.file_dropbox_test_application.R;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.FileModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.IModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.ui.adapter.RecyclerViewAdapter;

import java.text.SimpleDateFormat;
import java.util.Locale;

import androidx.annotation.NonNull;

public class FileViewHolder extends IViewHolder {

    private TextView name;
    private TextView size;
    private TextView date;

    public FileViewHolder(@NonNull View itemView, RecyclerViewAdapter adapter) {
        super(itemView);
        name = itemView.findViewById(R.id.text_name);
        size = itemView.findViewById(R.id.text_size);
        date = itemView.findViewById(R.id.text_date);
        itemView.setOnClickListener(view -> adapter.onItemClicked(this.getAdapterPosition()));
    }

    @Override
    public void bind(IModel model) {
        if (model instanceof FileModel){
            FileModel fileModel = (FileModel) model;
            name.setText(fileModel.getName());
            size.setText(fileModel.getSize());

            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
            date.setText(dateFormat.format(fileModel.getDate()));
        }
    }
}