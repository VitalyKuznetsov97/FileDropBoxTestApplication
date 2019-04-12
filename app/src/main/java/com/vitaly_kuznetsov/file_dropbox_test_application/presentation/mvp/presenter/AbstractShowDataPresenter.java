package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.presenter;

import com.vitaly_kuznetsov.file_dropbox_test_application.domain.entity.Entity;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.interactor.DefaultObserver;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mapper.ModelMapper;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.ErrorModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.IModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.view.IShowDataView;

import java.util.ArrayList;

abstract class AbstractShowDataPresenter extends BasePresenter<IShowDataView> implements IShowDataPresenter{

    private boolean loading;


    /**
     * IShowDataPresenter methods:
     * @param entities is later to be transformed into a correct data structure.
     */
    @Override
    public void showData(ArrayList<Entity> entities) {
        ArrayList<IModel> iModels = new ArrayList<>();
        for (Entity entity : entities) iModels.add(ModelMapper.transform(entity));
        getViewState().showData(iModels);
    }

    @Override
    public void onLoadingFinished() {
        loading = false;
        getViewState().hideLoading();
    }

    @Override
    public void showError(Exception exception) {
        ErrorModel errorModel = new ErrorModel();
        errorModel.setMessage(exception.getMessage());
        getViewState().showError(errorModel);
    }

    /**
     * UseCase commands:
     */
    abstract void startShowDataUseCase();

    final class LoadDataUseCaseObserver extends DefaultObserver<ArrayList<Entity>> {

        private IShowDataPresenter presenter;

        LoadDataUseCaseObserver(IShowDataPresenter presenter) {
            this.presenter = presenter;
        }

        @Override public void onComplete() {
            presenter.onLoadingFinished();
        }

        @Override public void onError(Throwable e) {
            presenter.onLoadingFinished();
            presenter.showError((Exception) e);
        }

        @Override public void onNext(ArrayList<Entity> entities) {
            presenter.showData(entities);
        }

    }

    /**
     * Other support methods:
     */
    void refreshData(){
        if (isLoading()) return;
        else loading = true;
        getViewState().showLoading();
        startShowDataUseCase();
    }

    boolean isLoading() { return loading; }
}
