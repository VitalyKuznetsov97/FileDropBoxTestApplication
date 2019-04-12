package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface IDirectoryNavigationView extends MvpView {

    void adaptUi(String directory);

}
