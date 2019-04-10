package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.ui.activity;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vitaly_kuznetsov.file_dropbox_test_application.R;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.ErrorModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.IModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.presenter.ShowDataPresenter;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.view.IShowDataView;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.navigation.Navigator;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.ui.controller.IShowDataController;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.ui.controller.RecyclerViewController;

import java.util.ArrayList;

public class MainActivity extends MvpAppCompatActivity implements IShowDataView {

    @InjectPresenter ShowDataPresenter showDataPresenter;
    private IShowDataController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new RecyclerViewController(this);
        findViewById(R.id.button_choose).setOnClickListener(view -> Navigator.navigateToChooseActivity(this));
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

}