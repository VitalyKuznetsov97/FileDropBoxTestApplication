package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.ui.adapter.view_holder;

import android.view.View;

import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.IModel;

import androidx.annotation.NonNull;

public class EmptyViewHolder extends IViewHolder {

    public EmptyViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    /**
     * Empty.
     */
    @Override
    public void bind(IModel model) { }
}
