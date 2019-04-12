package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.presenter;

import com.vitaly_kuznetsov.file_dropbox_test_application.domain.entity.Entity;

import java.util.ArrayList;

/**
 * This interface represents a presenter used by
 * {@link AbstractShowDataPresenter.LoadDataUseCaseObserver}
 */

public interface IShowDataPresenter {

    void showData(ArrayList<Entity> entities);
    void showError(Exception exception);
    void onLoadingFinished();

}
