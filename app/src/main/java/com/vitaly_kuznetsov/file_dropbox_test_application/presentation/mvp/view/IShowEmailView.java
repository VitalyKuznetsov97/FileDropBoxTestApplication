package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SingleStateStrategy.class)
public interface IShowEmailView extends MvpView {

    void showUserEmail(String email);

}