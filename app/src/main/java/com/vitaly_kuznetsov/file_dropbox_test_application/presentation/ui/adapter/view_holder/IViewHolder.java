package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.ui.adapter.view_holder;

import android.view.View;

import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.IModel;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class IViewHolder extends RecyclerView.ViewHolder {

    IViewHolder(@NonNull View itemView) { super(itemView); }

    public abstract void bind(IModel model);
}
