package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vitaly_kuznetsov.file_dropbox_test_application.R;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.ErrorModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.IModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.presenter.AllFilesFromFolderShowDataPresenter;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.presenter.DirectoryNavigationPresenter;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.presenter.ShowDetailsPresenter;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.view.IDirectoryNavigationView;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.view.IShowDataView;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.view.IShowDetailsView;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.ui.controller.IActivityItemClick;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.ui.controller.IShowDataController;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.ui.controller.RecyclerViewController;

import java.util.ArrayList;


public class ChooseActivity extends MvpAppCompatActivity implements IShowDataView, IShowDetailsView,
        IDirectoryNavigationView, IActivityItemClick {

    @InjectPresenter AllFilesFromFolderShowDataPresenter allFilesFromFolderShowDataPresenter;
    @InjectPresenter ShowDetailsPresenter showDetailsPresenter;
    @InjectPresenter DirectoryNavigationPresenter directoryNavigationPresenter;

    private IShowDataController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        controller = new RecyclerViewController(this);
        findViewById(R.id.constraint_layout_go_back).setOnClickListener(view -> onBackPressed());
        directoryNavigationPresenter.init(allFilesFromFolderShowDataPresenter);
    }

    /**
     * IShowDataView Methods.
     */
    @Override public void showLoading() {
        controller.showLoading(this);
    }

    @Override public void hideLoading() {
        controller.hideLoading();
    }

    @Override public void showData(ArrayList<IModel> iModels) {
        controller.showData(iModels);
    }

    @Override public void showError(ErrorModel errorModel) {
        controller.showError(errorModel);
    }

    /**
     * IShowDetailsView Methods.
     */
    @Override
    public void showUserEmail(String email) {
        TextView textView = findViewById(R.id.text_mail);
        textView.setText(email);
    }

    /**
     * IDirectoryNavigationView Methods.
     */
    @Override public void adaptUi(String directory) {
        TextView textDirectory = findViewById(R.id.text_directory);
        TextView textGoBack = findViewById(R.id.text_go_back);
        if (!directory.equals("")) {
            if (directory.length() > 20) directory = "..." + directory.substring(directory.length() - 17);
            textDirectory.setText(directory);
            textGoBack.setText(R.string.go_back);
            findViewById(R.id.image_go_back).setVisibility(View.VISIBLE);
        }
        else {
            textDirectory.setText(R.string.dropbox);
            textGoBack.setText(R.string.cancel);
            findViewById(R.id.image_go_back).setVisibility(View.GONE);
        }
    }

    @Override
    public void finishActivity() {
        finish();
    }

    /**
     * IActivityItemClick Methods.
     */
    @Override
    public void onItemClicked(IModel iModel) {
        directoryNavigationPresenter.onItemClicked(iModel);
    }

    @Override
    public void onBackPressed() { if (directoryNavigationPresenter.onBackPressed()) super.onBackPressed(); }
}
