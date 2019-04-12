package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.presenter;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.vitaly_kuznetsov.file_dropbox_test_application.data.repository.DpbxRepository;
import com.vitaly_kuznetsov.file_dropbox_test_application.data.repository.UserRepository;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.interactor.DefaultObserver;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.interactor.GetUserEmailUseCase;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.view.IShowEmailView;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.thread.UIThread;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.thread.UseCaseExecutionThread;

@InjectViewState
public class ShowEmailPresenter extends BasePresenter<IShowEmailView> {

    private GetUserEmailUseCase getUserEmailUseCase;

    /**
     * MVP methods:
     */
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getUserEmailUseCase = new GetUserEmailUseCase(new UseCaseExecutionThread(), new UIThread(), new UserRepository());
    }

    public void onResume(){
        startGetUserEmailUseCase();
    }

    /**
     * UseCase commands:
     */
    private void startGetUserEmailUseCase(){
        getUserEmailUseCase.execute(new OnLoggedUseCaseObserver(this), null);
    }

    private void onEmailReceived(String email){
        if (email != null && !(email.equals("")))
        getViewState().showUserEmail(email);
    }

    final class OnLoggedUseCaseObserver extends DefaultObserver<String> {

        private ShowEmailPresenter presenter;

        OnLoggedUseCaseObserver(ShowEmailPresenter presenter) {
            this.presenter = presenter;
        }

        @Override public void onNext(String email) {
            presenter.onEmailReceived(email);
        }
    }

}