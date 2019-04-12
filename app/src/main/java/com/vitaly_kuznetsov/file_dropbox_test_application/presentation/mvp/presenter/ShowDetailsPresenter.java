package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.vitaly_kuznetsov.file_dropbox_test_application.data.repository.DpbxRepository;
import com.vitaly_kuznetsov.file_dropbox_test_application.data.repository.UserRepository;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.interactor.DefaultObserver;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.interactor.GetUserDetailsUseCase;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.view.IShowDetailsView;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.thread.UIThread;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.thread.UseCaseExecutionThread;

@InjectViewState
public class ShowDetailsPresenter extends BasePresenter<IShowDetailsView> {

    private GetUserDetailsUseCase getUserEmailUseCase;
    private boolean logged;

    public boolean isLogged() {
        return logged;
    }

    /**
     * MVP methods:
     */
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getUserEmailUseCase = new GetUserDetailsUseCase(new UseCaseExecutionThread(), new UIThread(), new UserRepository());
    }

    @Override
    public void attachView(IShowDetailsView view) {
        super.attachView(view);
        DpbxRepository.checkLogged();
        startGetUserEmailUseCase();
    }

    /**
     * UseCase commands:
     */
    private void startGetUserEmailUseCase(){
        getUserEmailUseCase.execute(new OnLoggedUseCaseObserver(this), null);
    }

    private void onEmailReceived(String email){
        if (email != null && !(email.equals(""))) {
            getViewState().showUserEmail(email);
            logged = true;
        }
        else logged = false;
    }

    final class OnLoggedUseCaseObserver extends DefaultObserver<String> {

        private ShowDetailsPresenter presenter;

        OnLoggedUseCaseObserver(ShowDetailsPresenter presenter) {
            this.presenter = presenter;
        }

        @Override public void onNext(String email) {
            presenter.onEmailReceived(email);
        }
    }

}