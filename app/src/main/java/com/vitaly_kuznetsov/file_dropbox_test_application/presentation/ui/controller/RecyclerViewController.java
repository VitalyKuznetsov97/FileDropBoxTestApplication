package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.ui.controller;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.vitaly_kuznetsov.file_dropbox_test_application.R;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.ErrorModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.IModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.ui.adapter.RecyclerViewAdapter;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.ui.custom_view.CustomProgressBar;

import java.util.ArrayList;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewController implements IShowDataController {

    private RecyclerView recyclerView;
    private ConstraintLayout constraintLayout;
    private ProgressBar progressBar;
    private RecyclerViewAdapter adapter;
    private IActivityItemClick itemClick;

    public RecyclerViewController(Activity activity) {
        recyclerView = activity.findViewById(R.id.recycler_view);
        constraintLayout = activity.findViewById(R.id.constraint_recycler);

        if (activity instanceof IActivityItemClick)
            itemClick = (IActivityItemClick) activity;

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    public void onItemClicked(IModel iModel){
        if (itemClick != null) itemClick.onItemClicked(iModel);
    }

    @Override
    public void showData(ArrayList<IModel> iModels) {
        adapter.showData(iModels);
    }

    @Override
    public void showLoading(Context context) {
        progressBar = new CustomProgressBar(recyclerView, context, null, android.R.attr.progressBarStyle);
        recyclerView.setVisibility(View.INVISIBLE);
        constraintLayout.addView(progressBar);
    }

    @Override
    public void hideLoading() {
        if (progressBar != null) constraintLayout.removeView(progressBar);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(ErrorModel errorModel) {
        adapter.showError(errorModel);
    }
}