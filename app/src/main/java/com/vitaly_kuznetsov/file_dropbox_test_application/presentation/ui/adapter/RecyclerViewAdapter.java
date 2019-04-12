package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vitaly_kuznetsov.file_dropbox_test_application.R;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.EmptyModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.ErrorModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.IModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.ui.adapter.view_holder.DirectoryViewHolder;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.ui.adapter.view_holder.EmptyViewHolder;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.ui.adapter.view_holder.ErrorViewHolder;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.ui.adapter.view_holder.FileViewHolder;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.ui.adapter.view_holder.IViewHolder;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.ui.controller.RecyclerViewController;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.vitaly_kuznetsov.file_dropbox_test_application.presentation.constants.PresentationConstants.DIRECTORY;
import static com.vitaly_kuznetsov.file_dropbox_test_application.presentation.constants.PresentationConstants.EMPTY;
import static com.vitaly_kuznetsov.file_dropbox_test_application.presentation.constants.PresentationConstants.FILE;

public class RecyclerViewAdapter extends RecyclerView.Adapter<IViewHolder> {

    private RecyclerViewController recyclerViewController;
    private ArrayList<IModel> data = new ArrayList<>();

    public RecyclerViewAdapter(RecyclerViewController recyclerViewController) {
        this.recyclerViewController = recyclerViewController;
    }

    public void showData(ArrayList<IModel> iModels){
        data = iModels;
        if (data.size() == 0) data.add(new EmptyModel());
        notifyDataSetChanged();
    }

    public void showError(ErrorModel errorModel){
        data = new ArrayList<>();
        data.add(errorModel);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        IModel model = data.get(position);
        return model.getType();
    }

    @NonNull
    @Override
    public IViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == EMPTY) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_view_holder, parent, false);
            return new EmptyViewHolder(view);
        }
        else if (viewType == DIRECTORY) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.directory_view_holder, parent, false);
            return new DirectoryViewHolder(view, this);
        }
        else if (viewType == FILE){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.file_view_holder, parent, false);
            return new FileViewHolder(view, this);
        }
        else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.error_view_holder, parent, false);
            return new ErrorViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull IViewHolder holder, int position) {
        if (data.get(position) != null)
            holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void onItemClicked(int position){
        IModel iModel = data.get(position);
        recyclerViewController.onItemClicked(iModel);
    }
}
