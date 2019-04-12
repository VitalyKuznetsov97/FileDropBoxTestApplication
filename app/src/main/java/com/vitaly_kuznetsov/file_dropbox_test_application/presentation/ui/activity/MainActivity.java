package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.ui.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vitaly_kuznetsov.file_dropbox_test_application.R;
import com.vitaly_kuznetsov.file_dropbox_test_application.data.repository.DpbxRepository;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.ErrorModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.IModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.presenter.LastFileShowDataPresenter;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.presenter.ShowDetailsPresenter;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.view.IShowDataView;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.view.IShowDetailsView;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.navigation.Navigator;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.ui.controller.IShowDataController;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.ui.controller.RecyclerViewController;

import java.util.ArrayList;

public class MainActivity extends MvpAppCompatActivity implements IShowDataView, IShowDetailsView {

    @InjectPresenter LastFileShowDataPresenter lastFileShowDataPresenter;
    @InjectPresenter ShowDetailsPresenter showDetailsPresenter;

    private IShowDataController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new RecyclerViewController(this);
        findViewById(R.id.button_choose).setOnClickListener(view ->
            {
                if (!showDetailsPresenter.isLogged()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("You are not authorized.").setNegativeButton("Ok", (dialog, id) -> dialog.cancel());
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else Navigator.navigateToChooseActivity(this);
            });
        findViewById(R.id.button_log_in).setOnClickListener(view -> DpbxRepository.auth(this));
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

    @Override public void finishActivity() { finish(); }

    /**
     * IShowEmail Methods.
     */
    @Override
    public void showUserEmail(String email) {
        TextView textView = findViewById(R.id.text_user_mail);
        textView.setText(email);
    }
}